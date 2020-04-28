package ar.com.wolox.android.cookbook.tagsfilters

import ar.com.wolox.android.cookbook.common.network.networkCallback
import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope
import ar.com.wolox.wolmo.networking.exception.NetworkResourceException
import io.reactivex.Single
import javax.inject.Inject

@ApplicationScope
class TagsRepository @Inject constructor(private val tagService: TagService) {
// TODO: Change tagService if we move getTags to another services

    private var detailingTags: List<PokemonType>? = null
    private var detailingQuerySearch: String? = null

    fun getQuerySearch(): String? = detailingQuerySearch

    fun setQuerySearch(query: String) {
        detailingQuerySearch = query
    }

    fun clearQuerySearch() {
        detailingQuerySearch = null
    }

    fun getSelectedTags(): List<PokemonType>? = detailingTags?.filter { tag -> tag.isSelected }

    fun setSelectedTags(tags: List<PokemonType>) {
        detailingTags = tags
    }

    fun removeSelectedTags(tag: PokemonType) {
        detailingTags?.first { it.name == tag.name }?.isSelected = false
    }

    fun emptySearch(): Boolean {
        detailingTags?.let { it -> return !it.any { it.isSelected } && detailingQuerySearch.isNullOrBlank() }
        return (detailingTags.isNullOrEmpty() && detailingQuerySearch.isNullOrBlank())
    }

    fun hasTagsSelected(): Boolean {
        return !detailingTags?.filter { tag -> tag.isSelected }.isNullOrEmpty()
    }

    fun getTags(): List<PokemonType>? = detailingTags

    fun setTags(tags: List<PokemonType>) {
        detailingTags = tags
    }

    fun loadTags(): Single<List<PokemonType>> {
        return Single.create<List<PokemonType>> { emitter ->
            detailingTags?.let {
                emitter.onSuccess(it)
                return@create
            }

            tagService
                    .getTags()
                    .enqueue(networkCallback {
                        onResponseSuccessful { recipe ->
                            recipe!!.let {
                                detailingTags = it.results
                                emitter.onSuccess(it.results)
                            }
                        }

                        onCallFailure {
                            emitter.tryOnError(it!!)
                        }

                        onResponseFailed { _, errorCode ->
                            emitter.tryOnError(NetworkResourceException("", errorCode))
                        }
                    })
        }
    }
}