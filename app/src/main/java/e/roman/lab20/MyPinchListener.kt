package e.roman.lab20

import android.util.Log
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import android.widget.ImageView

class MyPinchListener(drawer: Drawer) : SimpleOnScaleGestureListener() {

    var span: Float = 10000f
    val drawer = drawer
    var size = 0f

    override fun onScale(detector: ScaleGestureDetector): Boolean {
        Log.d("pinch", "tt")
        if (detector.currentSpan > detector.previousSpan) {
            drawer.draw(detector.currentSpan / 20000)
        } else {
            drawer.draw(-detector.currentSpan / 20000)
        }
        return true
    }
}