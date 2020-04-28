package ar.com.wolox.android.cookbook.tagsfilters

data class TagPokemonType(
        val results: List<PokemonType>
)

data class PokemonType(
        val name: String,
        val url: String
){
    var isSelected = false
}