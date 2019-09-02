package ar.com.wolox.android.cookbook.room.dialog

interface RoomInputDialogListener {

    fun onPositiveButtonClicked(data: String)

    fun onNegativeButtonClicked()
}