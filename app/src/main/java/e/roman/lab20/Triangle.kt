package e.roman.lab20

import android.graphics.Canvas
import android.graphics.Paint
import android.util.Log
import kotlin.math.cos
import kotlin.math.sign
import kotlin.math.sin
import kotlin.math.sqrt

class Triangle(coords1: FloatArray, coords2: FloatArray, coords3: FloatArray, canvas: Canvas, paint: Paint, type: Int) {

    private var coords1 = coords1
    private var coords2 = coords2
    private var coords3 = coords3
    private val canvas = canvas
    private val paint = paint
    private val type = type
    private var size = 1f

    fun draw(angleX: Float, angleY: Float, size: Float){
        this.size += size
        val coords11 = floatArrayOf(
            coords1[0],
            coords1[1] * cos(angleY) - coords1[2] * sin(angleY),
            coords1[1] * sin(angleY) + coords1[2] * cos(angleY)
        )
        val coords21 = floatArrayOf(
            coords2[0],
            coords2[1] * cos(angleY) - coords2[2] * sin(angleY),
            coords2[1] * sin(angleY) + coords2[2] * cos(angleY)
        )
        val coords31 = floatArrayOf(
            coords3[0],
            coords3[1] * cos(angleY) - coords3[2] * sin(angleY),
            coords3[1] * sin(angleY) + coords3[2] * cos(angleY)
        )
        coords1 = floatArrayOf(
            coords11[0] * cos(angleX) + coords11[2] * sin(angleX),
            coords11[1],
            -coords11[0] * sin(angleX) + coords11[2] * cos(angleX)
        )
        coords2 = floatArrayOf(
            coords21[0] * cos(angleX) + coords21[2] * sin(angleX),
            coords21[1],
            -coords21[0] * sin(angleX) + coords21[2] * cos(angleX)
        )
        coords3 = floatArrayOf(
            coords31[0] * cos(angleX) + coords31[2] * sin(angleX),
            coords31[1],
            -coords31[0] * sin(angleX) + coords31[2] * cos(angleX)
        )
        if (((coords2[0] - coords1[0]) * (coords3[1] - coords1[1]) > (coords3[0] - coords1[0]) * (coords2[1] - coords1[1]) && type == 1) ||
            ((coords2[0] - coords1[0]) * (coords3[1] - coords1[1]) <= (coords3[0] - coords1[0]) * (coords2[1] - coords1[1]) && type == 2)) {
            canvas.drawLine(
                coords1[0] * this.size * 100 + 500f,
                coords1[1] * this.size * 100 + 500f,
                coords2[0] * this.size * 100 + 500f,
                coords2[1] * this.size * 100 + 500f,
                paint
            )
            canvas.drawLine(
                coords1[0] * this.size * 100 + 500f,
                coords1[1] * this.size * 100 + 500f,
                coords3[0] * this.size * 100 + 500f,
                coords3[1] * this.size * 100 + 500f,
                paint
            )
            canvas.drawLine(
                coords2[0] * this.size * 100 + 500f,
                coords2[1] * this.size * 100 + 500f,
                coords3[0] * this.size * 100 + 500f,
                coords3[1] * this.size * 100 + 500f,
                paint
            )
        }
    }
}