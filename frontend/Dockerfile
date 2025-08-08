# =========================================
# Stage 1: Build the Angular Application
# =========================================
# =========================================
# Stage 1: Build the Angular Application
# =========================================
ARG NODE_VERSION=22.14.0-alpine
ARG NGINX_VERSION=alpine3.21

# Use a lightweight Node.js image for building (customizable via ARG)
FROM node:${NODE_VERSION} AS builder

# Set the working directory inside the container
WORKDIR /app

# Install additional dependencies that might be needed for node-gyp and native modules
RUN apk add --no-cache python3 make g++ git

# Create .npmrc to handle potential npm issues
RUN echo "unsafe-perm=true" > /root/.npmrc && \
    echo "fund=false" >> /root/.npmrc && \
    echo "audit=false" >> /root/.npmrc

# Copy package-related files first to leverage Docker's caching mechanism
COPY package.json package-lock.json ./

# Debug: Show Node and npm versions
RUN node --version && npm --version

# Debug: Show package.json content
RUN cat package.json

# Clear npm cache and install dependencies with verbose logging
RUN npm cache clean --force
RUN npm ci --verbose --no-optional

# Copy the rest of the application source code into the container
COPY . .

# Build the Angular application with error handling
RUN npm run build --verbose

# =========================================
# Stage 2: Prepare Nginx to Serve Static Files
# =========================================

FROM nginxinc/nginx-unprivileged:${NGINX_VERSION} AS runner

# Use a built-in non-root user for security best practices
USER nginx

# Copy custom Nginx config
COPY nginx.conf /etc/nginx/nginx.conf

# Copy the static build output from the build stage to Nginx's default HTML serving directory
COPY --chown=nginx:nginx --from=builder /app/dist/*/browser /usr/share/nginx/html

# Expose port 8080 to allow HTTP traffic
EXPOSE 8080

# Start Nginx directly with custom config
ENTRYPOINT ["nginx", "-c", "/etc/nginx/nginx.conf"]
CMD ["-g", "daemon off;"]