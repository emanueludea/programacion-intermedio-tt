# Enable BuildKit for better caching
export DOCKER_BUILDKIT=1

# Build with cache mount support
docker build --progress=plain -t frontend:latest .

# Use cache from registry (for CI/CD)
docker build \
  --cache-from=frontend:latest \
  --cache-from=frontend:cache \
  -t frontend:latest .

# Push cache layers (for CI/CD)
docker build \
  --cache-from=frontend:latest \
  --cache-to=type=registry,ref=frontend:cache \
  -t frontend:latest .