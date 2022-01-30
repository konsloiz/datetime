resource "kubernetes_ingress" "datetime_ingress" {
  metadata {
    name = "datetime-ingress"
  }

  spec {
    backend {
      service_name = "datetime-service"
      service_port = 8080
    }

    rule {
      http {
        path {
          backend {
            service_name = "datetime-service"
            service_port = 8080
          }

          path = "/"
        }
      }
    }
  }
}