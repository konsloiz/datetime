terraform {
  required_providers {
    kubernetes = {
      source  = "hashicorp/kubernetes"
      version = "2.5.0"
    }
  }

  required_version = ">= 1.0.0"
}

provider "kubernetes" {
  config_path    = var.kind_cluster_config_path
}