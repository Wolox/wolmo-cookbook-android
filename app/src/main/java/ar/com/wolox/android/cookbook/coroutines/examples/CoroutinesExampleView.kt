package ar.com.wolox.android.cookbook.coroutines.examples

interface CoroutinesExampleView {

    fun closeView()

    fun showNumber(progress: Int)

    fun showOptions(options: List<CoroutinesExamplePresenter.CoroutinesExampleOption>)

    fun showMessage(message: String)
}
