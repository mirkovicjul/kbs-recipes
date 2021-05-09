package drools.fire

import org.kie.api.KieServices
import org.kie.api.runtime.{KieContainer, KieSession}
import org.kie.api.runtime.rule.FactHandle

import java.util.{HashMap => JHashMap, Scanner => JScanner}

object FireExample {

  lazy val run: Unit = {
    val kc: KieContainer     = KieServices.Factory.get.getKieClasspathContainer
    val ksession: KieSession = kc.newKieSession("FireKS")

    val names: Seq[String] = Seq("kitchen", "bedroom", "office", "livingroom")

    val name2room: JHashMap[String, Room] = new JHashMap[String, Room]()

    names.map { name =>
      val room: Room = new Room(name)
      name2room.put(name, room)
      ksession.insert(room)
      val sprinkler: Sprinkler = new Sprinkler(room)
      ksession.insert(sprinkler)
    }

    ksession.fireAllRules
    pause()

    val kitchenFire: Fire = new Fire(name2room.get("kitchen"))
    val officeFire: Fire  = new Fire(name2room.get("office"))

    val kitchenFireHandle: FactHandle = ksession.insert(kitchenFire)
    val officeFireHandle: FactHandle  = ksession.insert(officeFire)

    ksession.fireAllRules
    pause()

    ksession.delete(kitchenFireHandle)
    ksession.delete(officeFireHandle)
    ksession.fireAllRules
    ksession
      .dispose() // Stateful rule session must always be disposed when finished
  }

  def pause(): Unit = {
    System.out.println("Pressure enter to contnue")
    val keyboard: JScanner = new JScanner(System.in)
    keyboard.nextLine
  }

}
