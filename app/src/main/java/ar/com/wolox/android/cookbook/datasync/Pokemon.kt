package ar.com.wolox.android.cookbook.datasync

data class Pokemon(
    val id: Int,
    val name: String,
    private val sprites: Map<String, String>,
    private val types: List<TypeSlot>
) {

    val spriteUrls
        get() = sprites.values.toList()

    val firstType: Type
        get() = types.sortedBy { it.slot }[0].type

    val secondType: Type?
        get() = if (types.size > 1) types.sortedBy { it.slot }[1].type else null
}

data class TypeSlot(val slot: Int, val type: Type)

data class Type(val name: String)
