package ar.com.wolox.android.cookbook.recipepicker

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class RecipeItem(
    val recipe: Recipe,
    @DrawableRes val imageResId: Int,
    @StringRes val stringResId: Int
)