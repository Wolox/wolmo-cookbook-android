package ar.com.wolox.android.cookbook.common.utils

import android.util.Patterns

/** Validates that an email has a correct format. */
val String.isValidEmail get() = Patterns.EMAIL_ADDRESS.matcher(this).matches()