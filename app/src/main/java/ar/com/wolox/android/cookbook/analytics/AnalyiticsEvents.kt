// ktlint-disable filename
package ar.com.wolox.android.cookbook.analytics

import ar.com.wolox.android.cookbook.recipepicker.Recipe

class OpenRecipeEvent(recipe: Recipe) : AnalyticsEvent(listOf("recipe" to recipe.name))