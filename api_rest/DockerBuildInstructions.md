# Enable BuildKit
export DOCKER_BUILDKIT=1

# Build with cache
docker build --progress=plain -t api_rest:latest .

# Build with external cache (for CI/CD)
docker build \
  --cache-from=type=registry,ref=api_rest:cache \
  --cache-to=type=registry,ref=api_rest:cache,mode=max \
  -t api_rest:latest .

# Multi-platform build with cache
docker buildx build \
  --platform linux/amd64,linux/arm64 \
  --cache-from=type=registry,ref=api_rest:cache \
  --cache-to=type=registry,ref=api_rest:cache,mode=max \
  --push \
  -t api_rest:latest .