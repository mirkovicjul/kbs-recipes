package drools

import com.github.benmanes.caffeine.cache.{Cache, Caffeine}
import org.kie.api.runtime.{KieContainer, KieSession}
import drools.recommendation.{MadeRecipe, Recommendation, StartedEngine, StorageItem, User, Ingredient => IngredientFact, Measurement => MeasurementFact, Recipe => RecipeFact, RecipeIngredient => RecipeIngredientFact}
import org.kie.api.runtime.rule.EntryPoint

import java.time.LocalDate
import java.util
import java.util.concurrent.TimeUnit
import java.util.{ArrayList => JArrayList}
import javax.inject.Inject
import scala.jdk.CollectionConverters.{IterableHasAsScala, IteratorHasAsScala}

trait SessionCache {

  def simpleSession(userId: Long): KieSession

  def exist(userId: Long): Boolean

  def invalidateSimpleSession(userId: Long): Unit

  def addNewFactUserPreferences(fact: Long, userId: Long, ingredientId: Long): Unit

  def removeFactUserPreferences(fact: Long, userId: Long, ingredientId: Long): Unit

  def removeStartedEngine(session: KieSession): Unit

  def addFactHistoryItem(userId: Long, recipeId: Long, servings: Long, date: LocalDate): Unit

  def removeRecommendations(session: KieSession): Unit
}

class SessionCacheImpl @Inject()(kieContainer: KieContainer) extends SessionCache {

  private val simpleSessionCache: Cache[Long, KieSession] =
    Caffeine
      .newBuilder
      .expireAfterWrite(2, TimeUnit.DAYS)
      .maximumSize(10_000).build[Long, KieSession]

  override def simpleSession(userId: Long): KieSession =
    simpleSessionCache.get(userId, _ => kieContainer.newKieSession("Recommendation"))

  override def exist(userId: Long): Boolean = simpleSessionCache.asMap().keySet().contains(userId)

  override def invalidateSimpleSession(userId: Long): Unit = {
    Option(simpleSessionCache.getIfPresent(userId)).foreach(_.dispose())
    simpleSessionCache.invalidate(userId)
  }

  override def removeStartedEngine(session: KieSession): Unit = {
    val started: Seq[StartedEngine] = session
      .getQueryResults("Started")
      .iterator()
      .asScala
      .map(r => r.get("$started").asInstanceOf[StartedEngine])
      .toSeq

    started.map(session.getFactHandle(_)).foreach(session.delete)
  }

  override def removeRecommendations(session: KieSession): Unit ={
    val oldResults: Seq[Recommendation] = session
      .getQueryResults("AllRecommendations")
      .iterator()
      .asScala
      .map(r => r.get("$recommendation").asInstanceOf[Recommendation])
      .toSeq

    oldResults.map(session.getFactHandle(_)).foreach(session.delete)

  }
  override def addNewFactUserPreferences(fact: Long, userId: Long, ingredientId: Long) = {
    println("Adding new fact into session...")
    val session: KieSession = simpleSession(userId)

    removeStartedEngine(session)

    removeRecommendations(session)


    val userFact: Option[User] = session
      .getQueryResults("User")
      .iterator()
      .asScala
      .map(r => r.get("$user").asInstanceOf[User])
      .toSeq
      .headOption

    val ingredientsFacts: Seq[IngredientFact] = session
      .getQueryResults("Ingredients")
      .iterator()
      .asScala
      .map(r => r.get("$ingredients").asInstanceOf[IngredientFact])
      .toSeq

    val user = userFact.get

    val newFact: IngredientFact = ingredientsFacts.filter(f => f.getId == ingredientId).head
    val newFactList: util.List[IngredientFact] = new JArrayList[IngredientFact]()

    fact match {
      case 1 =>
        val likes: util.List[IngredientFact] = user.getLikes
        val userAlreadyLikes = likes.contains(newFact)
       if (!userAlreadyLikes) {
         likes.forEach(l => newFactList.add(l))
         newFactList.add(newFact)
         val newUser: User = new User(user.getId, newFactList, user.getAllergies, user.getDislikes, user.getUnavailable, user.getStorage)
         newUser.getLikes.forEach(x => println(s"User " + newUser.getId + " likes " + x.getName))
         session.update(session.getFactHandle(user), newUser)
       }
      case 2 =>
        val dislikes: util.List[IngredientFact] = user.getDislikes
        val userAlreadyDislikes = dislikes.contains(newFact)
        if (!userAlreadyDislikes) {
          dislikes.forEach(l => newFactList.add(l))
          newFactList.add(newFact)
          val newUser: User = new User(user.getId, user.getLikes, user.getAllergies, newFactList, user.getUnavailable, user.getStorage)
          newUser.getDislikes.forEach(x => println(s"User " + newUser.getId + " dislikes " + x.getName))
          session.update(session.getFactHandle(user), newUser)
        }
      case 3 =>
        val allergies: util.List[IngredientFact] = user.getAllergies
        val userAlreadyHasAllergy = allergies.contains(newFact)
        if (!userAlreadyHasAllergy) {
          allergies.forEach(l => newFactList.add(l))
          newFactList.add(newFact)
          val newUser: User = new User(user.getId, user.getLikes, newFactList, user.getDislikes, user.getUnavailable, user.getStorage)
          newUser.getAllergies.forEach(x => println(s"User " + newUser.getId + " is allergic to " + x.getName))
          session.update(session.getFactHandle(user), newUser)
        }
      case 4 =>
        val unavailable: util.List[IngredientFact] = user.getUnavailable
        val ingredientAlreadyUnavailable = unavailable.contains(newFact)
        if (!ingredientAlreadyUnavailable) {
          unavailable.forEach(l => newFactList.add(l))
          newFactList.add(newFact)
          val newUser: User = new User(user.getId, user.getLikes, user.getAllergies, user.getDislikes, newFactList, user.getStorage)
          newUser.getUnavailable.forEach(x => println(s"User " + newUser.getId + " can't find " + x.getName))
          session.update(session.getFactHandle(user), newUser)
        }
    }
  }

  override def removeFactUserPreferences(fact: Long, userId: Long, ingredientId: Long): Unit ={
    println("Removing fact from session...")
    val session: KieSession = simpleSession(userId)

    removeStartedEngine(session)

    removeRecommendations(session)

    val userFact: Option[User] = session
      .getQueryResults("User")
      .iterator()
      .asScala
      .map(r => r.get("$user").asInstanceOf[User])
      .toSeq
      .headOption

    val ingredientsFacts: Seq[IngredientFact] = session
      .getQueryResults("Ingredients")
      .iterator()
      .asScala
      .map(r => r.get("$ingredients").asInstanceOf[IngredientFact])
      .toSeq

    val user = userFact.get

    val factToRemove: IngredientFact = ingredientsFacts.filter(f => f.getId == ingredientId).head

    val newList: util.List[IngredientFact] = new JArrayList[IngredientFact]()

    fact match {
      case 1 =>
        val likes = user.getLikes
        likes.forEach(l => newList.add(l))
        newList.remove(factToRemove)
        val newUser: User = new User(user.getId, newList, user.getAllergies, user.getDislikes, user.getUnavailable, user.getStorage)
        newUser.getLikes.forEach(x=> println("User " + user.getId + " likes " + x.getName))
        session.update(session.getFactHandle(user), newUser)
      case 2 =>
        val dislikes = user.getDislikes
        dislikes.forEach(l => newList.add(l))
        newList.remove(factToRemove)
        val newUser: User = new User(user.getId, user.getLikes, user.getAllergies, newList, user.getUnavailable, user.getStorage)
        newUser.getDislikes.forEach(x=> println("User " + user.getId + " dislikes " + x.getName))
        session.update(session.getFactHandle(user), newUser)
      case 3 =>
        val allergies = user.getAllergies
        allergies.forEach(l => newList.add(l))
        newList.remove(factToRemove)
        val newUser: User = new User(user.getId, user.getLikes, newList, user.getDislikes, user.getUnavailable, user.getStorage)
        newUser.getAllergies.forEach(x=> println("User " + user.getId + " is allergic to " + x.getName))
        session.update(session.getFactHandle(user), newUser)
      case 4 =>
        val unavailable = user.getUnavailable
        unavailable.forEach(l => newList.add(l))
        newList.remove(factToRemove)
        val newUser: User = new User(user.getId, user.getLikes, user.getAllergies, user.getDislikes, newList, user.getStorage)
        newUser.getUnavailable.forEach(x=> println("User " + user.getId + " can't find " + x.getName))
        session.update(session.getFactHandle(user), newUser)
    }

    userFact.map(session.getFactHandle(_)).foreach(session.delete)

  }

  override def addFactHistoryItem(userId: Long, recipeId: Long, servings: Long, date: LocalDate): Unit ={
    val session: KieSession = simpleSession(userId)

    val historyEntryPoint: EntryPoint = session.getEntryPoint("$history")

    val recipes: Seq[RecipeFact] = session
      .getQueryResults("Recipes")
      .iterator()
      .asScala
      .map(r => r.get("$recipe").asInstanceOf[RecipeFact])
      .toSeq

    historyEntryPoint.insert(new MadeRecipe((recipes.filter(_.getId==recipeId).head), date, servings.toInt))

    removeStartedEngine(session)

    removeRecommendations(session)

  }


}
