package dev.olaore.customviewsamplesonandroid.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

class TouchPointsLinkView @JvmOverloads constructor(
    private val ctx: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(ctx, attributeSet, defStyleAttr, defStyleAttr) {

    private var touchX: Float = -1f
    private var touchY: Float = -1f

    private val points = mutableListOf<List<Float>>()
    private val pointsPath = Path()

    private val paint = Paint().apply {
        isAntiAlias = true
        color = Color.BLACK
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (points.size > 0) {
            updatePath(canvas)
        }

    }

    private fun drawPoint(canvas: Canvas?) {
        canvas?.run {
            drawCircle(touchX, touchY, 30f, paint)
        }
    }

    private fun updatePath(canvas: Canvas?) {
        if (points.size == 1) {
            pointsPath.moveTo(points[0][0], points[0][1])
        } else {
            val lastEntry = points[points.size - 1]
            pointsPath.lineTo(lastEntry[0], lastEntry[1])
        }

        canvas?.drawPath(pointsPath, paint)
    }

    fun clear() {
        points.clear()
        pointsPath.reset()
        invalidate()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                points.add(listOf(event.x, event.y))
                invalidate()
            }
        }
        return true
    }

}