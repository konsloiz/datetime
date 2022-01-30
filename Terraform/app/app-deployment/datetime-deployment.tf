resource "kubernetes_pod" "datetime_app" {
  metadata {
    name = "datetime-app"
    labels = {
      app = "datetime"
    }
  }

  spec {
    container {
      image = "konsloiz/datetime:v4.0"
      name  = "datetime"
      port  {
        container_port = "8080"
      }
    }
  }
}

resource "kubernetes_service" "datetime_service" {
  metadata {
    name = "datetime-service"
  }
  spec {
    selector = {
      app = "${kubernetes_pod.datetime_app.metadata.0.labels.app}"
    }
    port {
      port = "8080"
      target_port = "8080"
    }
    type = "ClusterIP"
  }
}