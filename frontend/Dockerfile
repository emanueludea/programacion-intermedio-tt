# syntax=docker/dockerfile:1

ARG NODE_VERSION=22.14.0-alpine
ARG NGINX_VERSION=alpine3.21

# =========================================
# Stage 1: Dependencies Cache Layer
# =========================================
FROM node:${NODE_VERSION} AS deps

WORKDIR /app

# Create non-root user
RUN addgroup -g 1001 -S nodegroup && \
    adduser -u 1001 -S nodeuser -G nodegroup && \
    chown -R nodeuser:nodegroup /app

USER nodeuser

# Copy package files
COPY --chown=nodeuser:nodegroup package.json package-lock.json ./

# Install dependencies with cache mount
RUN --mount=type=cache,target=/home/nodeuser/.npm,uid=1001,gid=1001 \
    --mount=type=cache,target=/app/node_modules,uid=1001,gid=1001,sharing=locked \
    npm ci --prefer-offline --no-audit --no-fund --silent

# =========================================
# Stage 2: Build the Angular Application
# =========================================
FROM node:${NODE_VERSION} AS builder

WORKDIR /app

# Create non-root user
RUN addgroup -g 1001 -S nodegroup && \
    adduser -u 1001 -S nodeuser -G nodegroup && \
    chown -R nodeuser:nodegroup /app

USER nodeuser

# Copy dependencies from previous stage
COPY --from=deps --chown=nodeuser:nodegroup /app/node_modules ./node_modules
COPY --from=deps --chown=nodeuser:nodegroup /app/package*.json ./

# Copy source code
COPY --chown=nodeuser:nodegroup . .

# Build with Angular cache mount
RUN --mount=type=cache,target=/home/nodeuser/.cache,uid=1001,gid=1001 \
    --mount=type=cache,target=/app/.angular/cache,uid=1001,gid=1001 \
    npm run build --configuration=production

# =========================================
# Stage 3: Production Nginx Server
# =========================================
FROM nginxinc/nginx-unprivileged:${NGINX_VERSION} AS runner

USER nginx

# Copy configuration
COPY --chown=nginx:nginx nginx.conf /etc/nginx/nginx.conf

# Copy built application
COPY --chown=nginx:nginx --from=builder /app/dist/*/browser /usr/share/nginx/html

# Set proper permissions
USER root
RUN find /usr/share/nginx/html -type f -exec chmod 644 {} \; && \
    find /usr/share/nginx/html -type d -exec chmod 755 {} \;
USER nginx

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
    CMD wget --no-verbose --tries=1 --spider http://localhost:8080/ || exit 1

ENTRYPOINT ["nginx", "-c", "/etc/nginx/nginx.conf"]
CMD ["-g", "daemon off;"]