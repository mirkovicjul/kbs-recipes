package modules

import org.kie.api.KieServices
import org.kie.api.runtime.KieContainer
import play.api.inject.ApplicationLifecycle

import javax.inject.{Inject, Provider}
import scala.concurrent.{ExecutionContext, Future}

class KieContainerProvider @Inject()(
    lifecycle: ApplicationLifecycle
)(implicit ec: ExecutionContext)
    extends Provider[KieContainer] {

  private val singleton: KieContainer =
    KieServices.Factory.get.getKieClasspathContainer

  override def get(): KieContainer = singleton

  lifecycle.addStopHook { () =>
    Future {
      singleton.dispose()
    }
  }

}
