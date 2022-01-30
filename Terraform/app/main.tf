module "app-deployment" {
  source   = "./app-deployment"

}

module "app-ingress" {
  source   = "./app-ingress"

  depends_on = [module.app-deployment]
}
