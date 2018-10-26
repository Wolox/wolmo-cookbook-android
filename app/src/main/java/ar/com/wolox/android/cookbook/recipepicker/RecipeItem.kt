package ar.com.wolox.android.cookbook.recipepicker

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes

data class RecipeItem(
    val recipe: Recipe,
    @DrawableRes val imageResId: Int,
    @StringRes val stringResId: Int
)