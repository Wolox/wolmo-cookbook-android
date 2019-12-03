package ar.com.wolox.android.cookbook.model

import android.os.Parcel
import android.os.Parcelable
import com.wolox.queries.GetOrdersQuery

data class ModelOrder(
    val id: String?,
    val variants: List<ModelVariant>?,
    val deliveryAddress: String?,
    val totalAmount: Int?,
    val user: ModelUser?
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.createTypedArrayList(ModelVariant.CREATOR),
            source.readString(),
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readParcelable<ModelUser>(ModelUser::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeTypedList(variants)
        writeString(deliveryAddress)
        writeValue(totalAmount)
        writeParcelable(user, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ModelOrder> = object : Parcelable.Creator<ModelOrder> {
            override fun createFromParcel(source: Parcel): ModelOrder = ModelOrder(source)
            override fun newArray(size: Int): Array<ModelOrder?> = arrayOfNulls(size)
        }

        fun fromGraphQl(order: GetOrdersQuery.Order): ModelOrder {
            return ModelOrder(order.id(), getVariantsList(order.variants()), order.deliveryAddress(), order.totalAmount(), ModelUser.fromGraphQl(order.user()))
        }

        private fun getVariantsList(variants: List<GetOrdersQuery.Variant>?): List<ModelVariant> {
            val variantList = arrayListOf<ModelVariant>()

            variants?.forEach {
                variantList.add(ModelVariant.fromGraphQl(it))
            }
            return variantList.toList()
        }
    }
}