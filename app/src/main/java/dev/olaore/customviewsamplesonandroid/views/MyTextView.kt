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
import androidx.core.view.setPadding
import dev.olaore.customviewsamplesonandroid.R

/**
 * TODO: document your custom view class.
 */
class MyTextView @JvmOverloads
    constructor(private val ctx: Context, private val attributeSet: AttributeSet? = null, private val defStyleAttr: Int = 0)
    : View(ctx, attributeSet, defStyleAttr) {

//    text variables and paint
    var myTextSize = 30f
    var text = "Drawing Text"
    var textPaint = Paint().apply {
        textAlign = Paint.Align.CENTER
        textSize = toDp(myTextSize).toFloat()
        color = Color.BLACK
        isAntiAlias = true
    }

//    view variables
    var extraPadding = 15

//    line variables and paint
    var lineSpacing = 20f
    var linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GRAY
        strokeWidth = 1.5f
    }
    var startX = 0f
    var startY = 0f
    var endX = 0f
    var endY = 0f

    var textBounds = Rect()

    init {
        setPadding(extraPadding, extraPadding, extraPadding, extraPadding)
        lineSpacing = (height / 4).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawLines(canvas)
        drawText(canvas)

    }

    private fun drawText(canvas: Canvas) {
        textPaint.getTextBounds(text, 0, text.length, textBounds)
        var textHeight = textBounds.height()
        var textWidth = textBounds.width()
        canvas.drawText(text, (width / 2).toFloat(), (height / 2).toFloat(), textPaint)
    }

    private fun drawLines(canvas: Canvas) {
        startX = paddingLeft.toFloat()
        lineSpacing = height / 4f
        for(cnt in 0..4) {
            if (cnt == 3) {
                linePaint.color = Color.BLACK
            }

            startY = paddingTop + (cnt * lineSpacing)
            endX = (width - paddingLeft - paddingRight).toFloat()
            endY = paddingTop + (cnt * lineSpacing)

            canvas.drawLine(startX, startY, endX, endY, linePaint)

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
