// ktlint-disable filename
package ar.com.wolox.android.cookbook.analytics

import ar.com.wolox.android.cookbook.analytics.core.AnalyticsEvent
import ar.com.wolox.android.cookbook.recipepicker.Recipe

class OpenRecipeEvent(recipe: Recipe) : AnalyticsEvent("open_recipe", listOf("recipe" to recipe.name))