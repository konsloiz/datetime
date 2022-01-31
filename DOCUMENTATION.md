# Task Documentation

## Task 1

*Create a small web service, in the language of your choice, which provides the current date and time under /now in a standard format. It should also be possible to request the date and time in at least one different format.*

> Solution

A simple SpringBoot application was developed. The application includes one Rest Controller with two GET methods. The first one returns under /now a string of the local UTC datetime in the [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601) standard format. The second one returns under /now/simple a sting of the local UTC datetime in a simpler format. Additionally, are included a simple test case to check the health status of the two endpoints and Swagger UI under swagger-ui/index.html for REST API documentation purposes.

The application was then built into a jar file. Then a Dockerfile was created to containerize the service. Inside the Dockerfile the jar is copied, a non-root user is specified to run the image, port 8080 is exposed and in the end the entrypoint is set to start the app.

The container was built, tagged and pushed to the Docker Hub Registry.

## Task 2

*The service needs to run inside a container on a local Kubernetes cluster. We would suggest to use kind. The deployment of the service needs to be performed via Terraform.*

> Solution

The Terraform Kubernetes provider is missing the depends_on functionality. This could create possible errors as the application deployment is highly depended on the cluster creation and the setup of the ingress controller. As a result, the developed Terraform configuration includes two parts:

1. Spinning up a kind cluster and deploying nginx ingress controller:

    - A [custom terraform provider](https://github.com/kyma-incubator/terraform-provider-kind) was used to create the new cluster.
    - The nginx ingress controller is deployed to the cluster via a helm chart
    - A null_resource resource is used to kubectl wait for the ingress controller to become ready


2. Deploying the app to the kind cluster:

    - The deployment is split in two modules, the app-deployment and the app-ingress
    - Initially the pod and its service are deployed to the cluster
    - Then the second module (depends_on the first module) is responsible to create the application-ingress in order to expose it outside the cluster
 

Additionally a Kubernetes yaml file for the application was developed to enable the deployment to any cluster independently from Terraform.

## Task 3

*Furthermore, you should also write a script, in a different language of your choice, which can be run externally to check the health of your service: if it is up and running and the clock is not desynchronized by more than 5 seconds.*

> Solution

A python script was developed. The script takes as an argument the url of the service that we want to check, and performs a request and initiates the following checks:

1. Checks if the status code of the app is 200
2. Compares the difference of the local UTC time with the response from the url and checks if time is desyncronized for more than (>5) seconds.

Everything is logged in both stdout and a log file.


## Task 4

*Automate as much as you can.*

> Solution

Two bash scripts were developed to automate the whole process:

1. The deploy.sh is responsible to create the kind cluster, install the nginx-ingress controller, deploy the datetime application to the cluster and perform a health check of the /now and /now/simple endpoints.

2. The destroy.sh is responsible to safely delete clean everything.
