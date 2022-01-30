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

## Creating a kind cluster and deploying the app

Please clone the repo by executing the following command:

```
git clone https://github.com/konsloiz/datetime.git
```

The easiest way to create the cluster and deploy the app is by running the [deploy.sh](https://github.com/konsloiz/datetime/blob/main/deploy.sh) script.

Open a bash terminal inside the cloned repo parent folder, and execute the following command:

```
bash ./deploy.sh
```

The script will automatically create a kind cluster, deploy an ingress controller, deploy the application, expose it, and perform health check for the /now and /simple endpoints.

The application is now available under threse endpoints:

1. http://localhost/now

2. http://localhost/now/simple

3. http://localhost/swagger-ui/index.html


To clean up everything safely, open a bash terminal inside the cloned repo parent folder, and execute the following command:

```
bash ./destroy.sh
```

To manually deploy the cluster and the ingress controller deployment:

```
cd /Terraform/k8s
terraform init
terraform apply -auto-approve
```
To manually deploy the application in the cluster:

```
cd Terraform/app
terraform init
terraform apply -auto-approve
```

## Performing a health check

To manually perform a health check for the service, you can run the [health_check.py script](https://github.com/konsloiz/datetime/blob/main/health_check.py) passing as an argument the url that you want to check.

Example 1:
```
python3 health_check.py --url http://localhost/now
```

Example 2:
```
python3 health_check.py --url http://localhost/now/simple
```


## Task Documentation

The documentation of the task is included [here](https://github.com/konsloiz/datetime/blob/main/DOCUMENTATION.md).
