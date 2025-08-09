# =========================================
# Stage 1: Build the Angular Application with Enhanced Caching
# =========================================
ARG NODE_VERSION=22.14.0-alpine
ARG NGINX_VERSION=alpine3.21

# Use a lightweight Node.js image for building (customizable via ARG)
FROM node:${NODE_VERSION} AS builder

# Set the working directory inside the container
WORKDIR /app

# Create a non-root user for the build process (security best practice)
RUN addgroup -g 1001 -S nodegroup && \
    adduser -u 1001 -S nodeuser -G nodegroup

# Change ownership of the working directory
RUN chown -R nodeuser:nodegroup /app

# Switch to non-root user
USER nodeuser

# Create cache directories with proper permissions
RUN mkdir -p /home/nodeuser/.npm /home/nodeuser/.cache

# Copy package-related files first to leverage Docker's caching mechanism
COPY --chown=nodeuser:nodegroup package.json package-lock.json ./

# Install project dependencies with multiple cache optimizations
RUN --mount=type=cache,target=/home/nodeuser/.npm,uid=1001,gid=1001 \
    --mount=type=cache,target=/home/nodeuser/.cache,uid=1001,gid=1001 \
    --mount=type=cache,target=/app/node_modules,uid=1001,gid=1001 \
    npm ci --prefer-offline --no-audit --no-fund --silent

# Copy source code (excluding node_modules due to .dockerignore)
COPY --chown=nodeuser:nodegroup . .

# Build the Angular application with build cache
RUN --mount=type=cache,target=/home/nodeuser/.cache,uid=1001,gid=1001 \
    --mount=type=cache,target=/app/.angular/cache,uid=1001,gid=1001 \
    npm run build --configuration=production

# =========================================
# Stage 2: Prepare Nginx to Serve Static Files
# =========================================

FROM nginxinc/nginx-unprivileged:${NGINX_VERSION} AS runner

# Use a built-in non-root user for security best practices
USER nginx

# Copy custom Nginx config with proper ownership
COPY --chown=nginx:nginx nginx.conf /etc/nginx/nginx.conf

# Copy the static build output from the build stage to Nginx's default HTML serving directory
COPY --chown=nginx:nginx --from=builder /app/dist/*/browser /usr/share/nginx/html

# Create a custom index.html fallback for SPA routing if it doesn't exist
RUN if [ ! -f /usr/share/nginx/html/index.html ]; then \
        echo '<!DOCTYPE html><html><head><title>App Loading...</title></head><body><h1>Loading...</h1></body></html>' > /usr/share/nginx/html/index.html; \
    fi

# Set proper permissions for static files
USER root
RUN chmod -R 644 /usr/share/nginx/html/* && \
    find /usr/share/nginx/html -type d -exec chmod 755 {} \;
USER nginx

# Expose port 8080 to allow HTTP traffic
# Note: The default NGINX container now listens on port 8080 instead of 80 
EXPOSE 8080

# Add health check for better container monitoring
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
    CMD curl -f http://localhost:8080/ || exit 1

# Start Nginx directly with custom config
ENTRYPOINT ["nginx", "-c", "/etc/nginx/nginx.conf"]
CMD ["-g", "daemon off;"]