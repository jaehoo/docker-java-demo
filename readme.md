# Dummy Java project 

This project is used to show how to deploy a single jar application using Docker.


Instructions:
```sh
# Create Java application
mavn clean package

# Create docker image
docker build -t dummy-demo .

# Run docker image into a container
docker run -dp 8000:8000 dummy-demo

```

Then the application can be showed into a web browser in the URL: http://localhost:8000/test

