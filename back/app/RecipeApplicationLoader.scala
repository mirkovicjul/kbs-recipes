import play.api.ApplicationLoader.Context
import play.api._
import play.api.routing.Router
import play.filters.HttpFiltersComponents

class RecipeApplicationLoader extends ApplicationLoader {
  def load(context: Context) = {
    new RecipeComponents(context).application
  }
}

class RecipeComponents(context: Context)
    extends BuiltInComponentsFromContext(context)
    with HttpFiltersComponents {

  lazy val router = Router.empty

}
