package drools

import com.github.benmanes.caffeine.cache.{Cache, Caffeine}
import org.kie.api.runtime.{KieContainer, KieSession}

import java.util.concurrent.TimeUnit
import javax.inject.Inject

trait SessionCache {

  def simpleSession(userId: Long): KieSession

  def invalidateSimpleSession(userId: Long): Unit

}

class SessionCacheImpl @Inject()(kieContainer: KieContainer) extends SessionCache {

  private val simpleSessionCache: Cache[Long, KieSession] =
    Caffeine
      .newBuilder
      .expireAfterWrite(2, TimeUnit.DAYS)
      .maximumSize(10_000).build[Long, KieSession]

  override def simpleSession(userId: Long): KieSession =
    simpleSessionCache.get(userId, _ => kieContainer.newKieSession("SimpleRecommendation"))

  override def invalidateSimpleSession(userId: Long): Unit = {
    Option(simpleSessionCache.getIfPresent(userId)).foreach(_.dispose())
    simpleSessionCache.invalidate(userId)
  }

}
