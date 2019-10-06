package ar.com.wolox.android.cookbook.model

import android.os.Parcel
import android.os.Parcelable
import com.wolox.queries.GetOrdersQuery

data class ModelVariant(
    val id: String?,
    val size: String?,
    val color: String?,
    val price: Int?,
    val imageUrl: String?
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(size)
        writeString(color)
        writeValue(price)
        writeString(imageUrl)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ModelVariant> = object : Parcelable.Creator<ModelVariant> {
            override fun createFromParcel(source: Parcel): ModelVariant = ModelVariant(source)
            override fun newArray(size: Int): Array<ModelVariant?> = arrayOfNulls(size)
        }

        fun fromGraphQl(variant: GetOrdersQuery.Variant): ModelVariant {
            return ModelVariant(variant.id(), variant.size(), variant.color(), variant.price(), variant.imageUrl())
        }
    }
}