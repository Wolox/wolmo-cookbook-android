package ar.com.wolox.android.cookbook.model

import android.os.Parcel
import android.os.Parcelable
import com.wolox.queries.GetOrdersQuery

data class ModelUser(
    val name: String?,
    val username: String?,
    val email: String?,
    val id: String?
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeString(username)
        writeString(email)
        writeString(id)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ModelUser> = object : Parcelable.Creator<ModelUser> {
            override fun createFromParcel(source: Parcel): ModelUser = ModelUser(source)
            override fun newArray(size: Int): Array<ModelUser?> = arrayOfNulls(size)
        }

        fun fromGraphQl(user: GetOrdersQuery.User): ModelUser {
            return ModelUser(user.name(), user.username(), user.email(), user.id())
        }
    }
}