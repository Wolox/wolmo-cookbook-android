package ar.com.wolox.android.cookbook.analytics

fun String.toLowerSnakeCase(): String {
    return if (this.isEmpty())
        this
    else
        StringBuilder(this.length).also {
            var seenSeparator = true
            var seenUpperCase = false

            this.forEach { c ->
                when (c) {
                    in '0'..'9' -> {
                        it.append(c)
                        seenSeparator = false
                        seenUpperCase = false
                    }
                    in 'a'..'z' -> {
                        it.append(c)
                        seenSeparator = false
                        seenUpperCase = false
                    }
                    in 'A'..'Z' -> {
                        if (!seenSeparator && !seenUpperCase) it.append('_')
                        it.append(c.toLowerCase())
                        seenSeparator = false
                        seenUpperCase = true
                    }
                    else -> {
                        if (!seenSeparator || !seenUpperCase) it.append('_')
                        seenSeparator = true
                        seenUpperCase = false
                    }
                }
            }
        }.toString()
}