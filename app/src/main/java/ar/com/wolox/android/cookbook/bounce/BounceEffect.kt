package ar.com.wolox.android.cookbook.bounce

import android.graphics.Canvas
import android.widget.EdgeEffect
import androidx.dynamicanimation.animation.DynamicAnimation.ViewProperty
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.EdgeEffectFactory
import ar.com.wolox.android.cookbook.bounce.BounceOrientation.VERTICAL

/** overscrollTranslation: The magnitude of translation distance while the list is over-scrolled. */
/** flingTranslation: The magnitude of translation distance when the list reaches the edge on fling. */
class BounceEffect(
    var orientation: BounceOrientation,
    var flingTranslation: Float = 0.5f,
    var overscrollTranslation: Float = 0.2f
) : EdgeEffectFactory() {

    override fun createEdgeEffect(recyclerView: RecyclerView, directionEffect: Int): EdgeEffect {

        return object : EdgeEffect(recyclerView.context) {
            var translationAnim: SpringAnimation? = null

            override fun onPull(deltaDistance: Float) {
                super.onPull(deltaDistance)
                handlePull(deltaDistance)
            }

            override fun onPull(deltaDistance: Float, displacement: Float) {
                super.onPull(deltaDistance, displacement)
                handlePull(deltaDistance)
            }

            private fun handlePull(deltaDistance: Float) {

                val sign = if (directionEffect == DIRECTION_BOTTOM) -1 else 1
                val translationYDelta =
                    sign * recyclerView.width * deltaDistance * overscrollTranslation
                recyclerView.translationY += translationYDelta

                translationAnim?.cancel()
            }

            override fun onRelease() {
                super.onRelease()

                if (recyclerView.translationY != 0f) {
                    translationAnim = createAnim()?.also { it.start() }
                }
            }

            override fun onAbsorb(velocity: Int) {
                super.onAbsorb(velocity)

                val translationVelocity = getSign() * velocity * flingTranslation
                translationAnim?.cancel()
                translationAnim =
                    createAnim().setStartVelocity(translationVelocity)?.also { it.start() }
            }

            override fun draw(canvas: Canvas?): Boolean {
                return false
            }

            override fun isFinished(): Boolean {
                return translationAnim?.isRunning?.not() ?: true
            }

            private fun createAnim() =
                SpringAnimation(recyclerView, getSpringAnimation())
                    .setSpring(
                        SpringForce()
                            .setFinalPosition(0f)
                            .setDampingRatio(SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY)
                            .setStiffness(SpringForce.STIFFNESS_LOW)
                    )

            private fun getSign(): Int {
                return if (orientation == VERTICAL) {
                    if (directionEffect == DIRECTION_BOTTOM) -1 else 1
                } else {
                    if (directionEffect == DIRECTION_LEFT) 1 else -1
                }
            }

            private fun getSpringAnimation(): ViewProperty {
                return if (orientation == VERTICAL) {
                    SpringAnimation.TRANSLATION_Y
                } else {
                    SpringAnimation.TRANSLATION_X
                }
            }
        }
    }
}

enum class BounceOrientation {
    VERTICAL,
    HORIZONTAL
}