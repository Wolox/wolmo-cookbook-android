package ar.com.wolox.android.cookbook.tagsfilters

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import ar.com.wolox.android.cookbook.R
import ar.com.wolox.wolmo.core.extensions.inflate
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.item_tag_type_container.view.*

typealias OnChipClickedListener = (Tag, Boolean) -> Unit
typealias OnChipClosedListener = (Tag) -> Unit

@SuppressLint("ViewConstructor")
class TypeTagItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    isCloseable: Boolean
) : LinearLayout(context, attrs, defStyleAttr) {

    var onChipClickedListener: OnChipClickedListener? = null
    var onChipClosedListener: OnChipClosedListener? = null
    private var isChipCloseable = isCloseable

    init {
        inflate(R.layout.item_tag_type_container, true)
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        orientation = VERTICAL
        this.layoutParams = layoutParams
    }

    fun setTitle(type: String) {
        vTypeTagTitle.text = type
    }

    @SuppressLint("InflateParams")
    fun populate(tagList: List<Tag>) {
        tagList.forEach { tag ->
            val chip = LayoutInflater.from(context).inflate(R.layout.item_tag_chip, null, false) as Chip
            chip.apply {
                text = tag.name
                isChecked = tag.isSelected
                setOnClickListener {
                    tag.isSelected = !tag.isSelected
                    onChipClickedListener?.invoke(tag, isChecked)
                }
                isCloseIconVisible = isChipCloseable
                if (isChipCloseable) {
                    isCheckable = false
                    closeIcon = resources.getDrawable(R.drawable.ic_close)
                    closeIconTint = resources.getColorStateList(R.color.color_red)
                    setOnCloseIconClickListener {
                        removeChip(this)
                        onChipClosedListener?.invoke(tag)
                    }
                }
            }
            vChipContainer.addView(chip)
        }
    }

    private fun removeChip(chip: Chip) {
        vChipContainer.removeView(chip)
    }
}