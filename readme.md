# Dummy Java project 

This project is used to show how to deploy a single jar application using Docker.


Instructions:
```sh
# Create Java application
mavn clean package

# Create Java application skiping tests
mavn clean package -DskipTests

# Create docker image
docker build -t dummy-demo .

# Run docker image into a container
docker run -dp 8000:8000 dummy-demo

# Run docker image with a bind mount (Windows Powershell)
docker run -dp 8000:8000 `
    -w /demo -v "$(pwd):/demo" `
    dummy-demo `
    sh -c "java -jar target/demo-1.0-SNAPSHOT.jar start"

# Run docker image with a bind mount (Linux)
docker run -dp 8000:8000 \
    -w /demo -v "$(pwd):/demo" \
    dummy-demo \
    sh -c "java -jar target/demo-1.0-SNAPSHOT.jar start"
```

Then the application can be showed into a web browser in the URL: http://localhost:8000/test

