package e.roman.lab20

import android.annotation.SuppressLint
import android.app.usage.UsageEvents
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.view.View.OnTouchListener
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat.getAction
import androidx.core.view.accessibility.AccessibilityEventCompat.getAction
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


class MainActivity : AppCompatActivity(), Drawer {

    private lateinit var imView: ImageView
    private lateinit var bitmap: Bitmap
    private lateinit var canvas: Canvas
    private var previousX: Float = 0f
    private var previousY: Float = 0f
    private lateinit var pyramid: MutableList<Triangle>
    private lateinit var bottom: MutableList<Triangle>
    var mPtrCount = 0

    private lateinit var text: TextView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById(R.id.text)
        imView = findViewById(R.id.im_view)
        bitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888)
        imView.setImageBitmap(bitmap)
        canvas = Canvas(bitmap)
        val paint = Paint()
        paint.color = Color.BLACK

        pyramid = mutableListOf()
        bottom = mutableListOf()
        var co1: Float
        var si1: Float
        var co2: Float
        var si2: Float
        for (i in 0..15){
            co1 = cos(2 * PI.toFloat() * i / 16)
            co2 = cos(2 * PI.toFloat() * (i + 1 ) / 16)
            si1 = sin(2 * PI.toFloat() * i / 16)
            si2 = sin(2 * PI.toFloat() * (i + 1) / 16)
            bottom.add(Triangle(floatArrayOf(co1 * 2, 0f, si1 * 2), floatArrayOf(co2 * 2, 0f, si2 * 2), floatArrayOf(0f, 0f, 0f), canvas, paint, 2))
            pyramid.add(Triangle(floatArrayOf(co1 * 2, 0f, si1 * 2), floatArrayOf(co2 * 2, 0f, si2 * 2), floatArrayOf(0f, -2f, 0f), canvas, paint, 1))
        }

        for (i in bottom)
            i.draw(0f, 0f, 0f)
        for (i in pyramid)
            i.draw(0f, 0f, 0f)

        val mScaleDetector = ScaleGestureDetector(this, MyPinchListener(this))
        imView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent): Boolean {
                val action: Int = event.action and MotionEvent.ACTION_MASK
                when (action) {
                    MotionEvent.ACTION_POINTER_DOWN -> mPtrCount++
                    MotionEvent.ACTION_POINTER_UP -> mPtrCount--
                    MotionEvent.ACTION_DOWN -> mPtrCount++
                    MotionEvent.ACTION_UP -> mPtrCount--
                }
                Log.d("pinch", "$mPtrCount")
                if (mPtrCount == 1)
                    onTouchEvent(event)
                else
                    mScaleDetector.onTouchEvent(event)
                return true
            }
        })
        /*imView.setOnTouchListener { view, event ->
            val x: Float = event.x
            val y: Float = event.y

            when (event.action) {
                MotionEvent.ACTION_MOVE -> {
                    var dx: Float = x - previousX
                    var dy: Float = y - previousY

                    canvas.drawColor(Color.WHITE)
                    for (i in bottom)
                        i.draw(dx / 100, -dy / 100, 0f)
                    for (i in pyramid)
                        i.draw(dx / 100, -dy / 100, 0f)
                    imView.setImageBitmap(bitmap)
                    text.text = dx.toString() + " : " + dy.toString()

                }
            }
            previousX = x
            previousY = y
            true
        }*/
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x: Float = event.x
        val y: Float = event.y

        when (event.action) {
            MotionEvent.ACTION_MOVE -> {
                var dx: Float = x - previousX
                var dy: Float = y - previousY

                canvas.drawColor(Color.WHITE)
                for (i in bottom)
                    i.draw(dx / 100, -dy / 100, 0f)
                for (i in pyramid)
                    i.draw(dx / 100, -dy / 100, 0f)
                imView.setImageBitmap(bitmap)
                text.text = dx.toString() + " : " + dy.toString()

            }
        }
        previousX = x
        previousY = y
        return true
    }

    override fun draw(size: Float) {
        text.text = text.text
        canvas.drawColor(Color.WHITE)
        for (i in bottom)
            i.draw(0f, 0f, size)
        for (i in pyramid)
            i.draw(0f, 0f, size)
        imView.setImageBitmap(bitmap)
    }
}