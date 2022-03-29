#!/bin/bash

set -e

cd Terraform/k8s

echo "Creating a kind cluster"

terraform init
terraform apply -auto-approve

echo "The installation of the kind cluster is complete"

echo "Deploying the datetime application"

cd ../app

terraform init
terraform apply -auto-approve

echo "The installation of the datetime application is complete"

cd ..

cd ..

echo "Initializing the health check"

sleep 10

echo "Health check for: http://localhost/now"

python3 health_check.py --url http://localhost/now

echo "Health check for: http://localhost/now/simple"

python3 health_check.py --url http://localhost/now/simple
