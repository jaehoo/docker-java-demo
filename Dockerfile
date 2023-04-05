FROM alpine:3.14

# Exposed port
EXPOSE 8000

# Adding build tools to make yarn install work on Apple silicon / arm64 machines
RUN apk --update-cache add openjdk11
WORKDIR /demo
COPY . .
CMD ["java","-jar", "target/demo-1.0-SNAPSHOT.jar", "start"]



