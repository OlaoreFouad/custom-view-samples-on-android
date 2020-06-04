package dev.olaore.customviewsamplesonandroid.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import dev.olaore.customviewsamplesonandroid.R

/**
 * TODO: document your custom view class.
 */
class MyTextView @JvmOverloads
    constructor(private val ctx: Context, private val attributeSet: AttributeSet? = null, private val defStyleAttr: Int = 0)
    : View(ctx, attributeSet, defStyleAttr) {

    var textSize = 20f
    var text = "Drawing Text"
    var textPaint = Paint().apply {
        textAlign = Paint.Align.CENTER
        textSize = toDp(textSize).toFloat()
        color = Color.RED
        isAntiAlias = true
    }

    var lineStartX = 5f
    var lineStartY = 5f
    var lineStopX = 0f
    var lineStopY = 0f

    var lineSpacing = 20f
    var linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GRAY
        strokeWidth = 1.5f
    }

    var textBounds = Rect()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawLines(canvas)

        textPaint.getTextBounds(text, 0, text.length, textBounds)
        var height = textBounds.height()
        var width = textBounds.width()
        canvas.drawText(text, width.toFloat() / 2, height + 10f, textPaint)

    }

    private fun drawLines(canvas: Canvas) {
        lineStopX = width - 10f
        for(cnt in 0..4) {
            if (cnt == 3) {
                linePaint.color = Color.BLACK
            }

            lineStartY = (cnt * lineSpacing) + 5f
            lineStopY = (cnt * lineSpacing) + 5f

            canvas.drawLine(lineStartX, lineStopX, lineStartY, lineStopY, linePaint)

        }
    }

    private fun toDp(value: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value,
            resources.displayMetrics
        ).toInt()
    }
}
