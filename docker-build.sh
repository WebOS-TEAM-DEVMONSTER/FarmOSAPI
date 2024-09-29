./gradlew build

 docker buildx build --platform linux/amd64,linux/arm64/v8 -t  snowmate318/farmos-app --push .

