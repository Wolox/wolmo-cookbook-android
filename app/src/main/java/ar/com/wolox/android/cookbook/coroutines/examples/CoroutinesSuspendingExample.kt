// package ar.com.wolox.android.cookbook.coroutines.examples
//
// import android.animation.Animator
// import android.graphics.Point
// import android.view.ViewPropertyAnimator
// import ar.com.wolox.android.cookbook.R
// import kotlinx.android.synthetic.main.fragment_coroutines_base_example.*
// import kotlin.coroutines.Continuation
// import kotlin.coroutines.resume
// import kotlin.coroutines.suspendCoroutine
//
// suspend fun CoroutinesRecipeItemFragment.animateExample() {
//    val display = requireActivity().windowManager.defaultDisplay
//    val size = Point().also { display.getSize(it) }
//    animateView.x = 0f
//    animateView.y = 0f
//    var a = true
//    animateView.animate()
//            .setDuration(5_000)
//            .y(size.y.toFloat() - animateView.height)
//            .x(size.x.toFloat() - animateView.width)
//            .setUpdateListener {
//                if (a) {
//                    container.setBackgroundResource(R.color.colorPrimaryDark)
//                } else {
//                    container.setBackgroundResource(R.color.colorAccent)
//                }
//                a = !a
//            }
//            .startSuspended()
//    container.setBackgroundResource(R.color.colorPrimary)
// }
//
// class AnimatorListenerBuilder(private val continuation: Continuation<Unit>) {
//    var onRepeat: () -> Unit = {}
//    var onCancel: () -> Unit = {}
//    var onStart: () -> Unit = {}
//
//    fun build() = object : Animator.AnimatorListener {
//        override fun onAnimationRepeat(p0: Animator) = onRepeat()
//        override fun onAnimationCancel(p0: Animator?) = onCancel()
//        override fun onAnimationStart(p0: Animator?) = onStart()
//        override fun onAnimationEnd(p0: Animator?) {
//            continuation.resume(Unit)
//        }
//    }
// }
//
// suspend fun ViewPropertyAnimator.startSuspended(listener: AnimatorListenerBuilder.() -> Unit = {}) = suspendCoroutine<Unit> {
//    setListener(AnimatorListenerBuilder(it).apply(listener).build())
//    start()
// }