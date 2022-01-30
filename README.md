# datetime
A simple containerized service to get the current date &amp; time

## Task Overview 

Create a small web service, in the language of your choice, which provides the current date and time under /now in a standard format. It should also be possible to request the date and time in at least one different format. The service needs to run inside a container on a local Kubernetes cluster. We would suggest to use kind. The deployment of the service needs to be performed via Terraform. Furthermore, you should also write a script, in a different language of your choice, which can be run externally to check the health of your service: if it is up and running and the clock is not desynchronized by more than 5 seconds. Automate as much as you can. Assume that your work will be reviewed by developers which are good at writing software but have no prior knowledge about containers, k8s and Terraform, so document accordingly.

## Requirements

| Requirements | Link                                            |
|--------------|-------------------------------------------------|
| Docker       | https://www.docker.com/products/docker-desktop  |
| Bash         | https://en.wikipedia.org/wiki/Bash_(Unix_shell) |
| Python3      | https://www.python.org/download/releases/3.0/   |
| kind         | https://kind.sigs.k8s.io/docs/user/quick-start/ |
| Terraform    | https://www.terraform.io/downloads              |

## Running the app

If you want to test the application you can simple run the docker container by applying the following command:

```
docker run -d -p8080:8080 konsloiz/datetime:v4.0
```

When the command is sucssefully executed, open any browser and visit the following endpoints:

1. http://localhost:8080/now - To get the UTC datetime in the [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601) standard format.

2. http://localhost:8080/now/simple - To get the UTC datetime in a simple format.

3. http://localhost:8080/swagger-ui/index.html - To get the Swagger UI with the REST API documentation.

To stop the application:

```
docker ps
docker stop "CONTAINER ID here"
```

## Deploying the app

## Task Documentation

The documentation of the task is included [here](https://github.com/konsloiz/datetime/blob/main/DOCUMENTATION.md).
