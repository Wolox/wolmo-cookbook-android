package ar.com.wolox.android.cookbook.recipepicker

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ar.com.wolox.android.cookbook.R

data class RecipeItem(
    val recipe: Recipe,
    @DrawableRes val imageResId: Int,
    @StringRes val stringResId: Int,
    @ColorRes val backgroundResId: Int = R.color.px_white
)
