package ar.com.wolox.android.cookbook.tagsfilters

import android.annotation.SuppressLint
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class TagsPresenter @Inject constructor(
        private val tagsRepository: TagsRepository,
        private val tagsService: TagService,
        @Named("main") private val mainScheduler: Scheduler,
        @Named("background") private val backgroundScheduler: Scheduler
) : BasePresenter<TagsView>() {

    private lateinit var tags: List<PokemonType>

    @SuppressLint("CheckResult")
    override fun onViewAttached() {
        tags = tagsRepository.getTags()!!
        tagsSetup()
    }

    private fun tagsSetup() {
        showTagsGroupByType()
    }

    private fun showTagsGroupByType() {
        view?.showTags(tags.groupBy { "Filtros" })
    }

    fun toggleAndStoreSelectedTag(tag: PokemonType, isChecked: Boolean) {
        tags.first { it.name  == tag.name }.isSelected = isChecked

    }

    fun saveSelectedTags() {
        tagsRepository.setSelectedTags(tags)
        view?.goBackToHome()
    }
}