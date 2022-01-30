variable "kind_cluster_config_path" {
  type        = string
  description = "The location where this cluster's kubeconfig is saved to."
  default     = "~/.kube/config"
}

variable "kind_cluster_config_context" {
  type        = string
  description = "Context to choose from the config file."
  default     = "kind-local-datetime"
}