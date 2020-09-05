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

class CircularArcView @JvmOverloads
    constructor(private val ctx: Context, private val attributeSet: AttributeSet? = null, private val defStyleAttr: Int = 0)
    : View(ctx, attributeSet, defStyleAttr) {

    private val arcPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#3E978B")
        style = Paint.Style.STROKE
        strokeWidth = 50f
    }
    private val textPaint = Paint().apply {
        color = Color.parseColor("#133B5C")
        textSize = toDP(40f)
        textAlign = Paint.Align.CENTER
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawArc(canvas)
        drawText(canvas)
    }

    private fun drawArc(canvas: Canvas?) {
        canvas?.let {
            it.drawArc(
                30f, 30f, width.toFloat() - 30f, height.toFloat() - 30f, 0f, 360f, false, arcPaint
            )
        }
    }

    private fun drawText(canvas: Canvas?) {
        canvas?.let {
            val rect = Rect()
            textPaint.getTextBounds("30%", 0, "30%".length, rect)
            it.drawText(
                "30%", 0, "30%".length, (width / 2).toFloat(), (height / 2).toFloat() + (rect.height() / 2), textPaint
            )
        }
    }

    private fun toDP(value: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics
        )
    }

}