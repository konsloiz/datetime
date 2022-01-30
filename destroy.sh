#!/bin/bash

set -e

cd Terraform/app

echo "Destroying the datetime application deployment"

terraform destroy -auto-approve

echo "Destroy of application complete"

echo "Destroying the kind cluster"

cd ../k8s

terraform destroy -auto-approve

echo "Destroy of the cluster complete"