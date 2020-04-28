package ar.com.wolox.android.cookbook.tagsfilters

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import kotlinx.android.synthetic.main.fragment_tags.*
import javax.inject.Inject

typealias OnBackListener = () -> Unit

class TagsFragment : WolmoFragment<TagsPresenter>(), TagsView {

    @Inject
    lateinit var toastFactory: ToastFactory

    var onBackListener: OnBackListener? = null

    override fun init() {
    }

    override fun layout() = R.layout.fragment_tags

    @SuppressLint("InflateParams")
    override fun showTags(tagsByType: Map<String, List<PokemonType>>) {
        tagsByType.forEach { type ->
            val tagContainer = TypeTagItem(requireContext(), isCloseable = false).apply {
                setTitle(type.key)
                populate(type.value)
                onChipClickedListener = { tag, isChecked -> presenter.toggleAndStoreSelectedTag(tag, isChecked) }
            }
            vTagTypeContainer.addView(tagContainer)
        }
        // This is the only way I found to get a bottom margin inside a linear layout that is being inflate programatically and that is inside a scrollview
        vTagTypeContainer.addView(View(requireContext()).apply { layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, BOTTOM_VIEW_HEIGHT) })
    }

    override fun setListeners() {
        vTagViewCancel.setOnClickListener { presenter.saveSelectedTags() }
    }

    override fun goBackToHome() {
        requireActivity().supportFragmentManager
                .popBackStack()
        onBackListener?.invoke()
    }

    override fun showError() {
        toastFactory.show(getString(R.string.unknown_error))
    }

    override fun onBackPressed(): Boolean {
        presenter.saveSelectedTags()
        return true
    }

    companion object {

        const val BOTTOM_VIEW_HEIGHT = 100

        fun newInstance() = TagsFragment()
    }
}

interface TagsView {

    fun showTags(tagsByType: Map<String, List<PokemonType>>)

    fun showError()

    fun goBackToHome()
}