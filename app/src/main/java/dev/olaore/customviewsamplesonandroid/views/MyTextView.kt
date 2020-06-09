package dev.olaore.customviewsamplesonandroid.views

import android.content.Context
import android.graphics.*
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
    private var myTextSize = 30f
    private var align = Paint.Align.CENTER

    private var fillStyle = Paint.Style.FILL

    private var text = "Sample Text"
    var textPaint = Paint().apply {
        textAlign = this@MyTextView.align
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

        var textX = textHeight
        var textY = textWidth

        if (this.align == Paint.Align.CENTER) {
            textX = (width / 2)
            textY = (height / 2)
        } else if (this.align == Paint.Align.LEFT) {
            textY = (height / 2)
            textX = textWidth + 10
        } else if (this.align == Paint.Align.RIGHT) {
            textY = (height / 2)
            textX = width - textWidth
        }

        canvas.drawText(text, textX.toFloat(), textY.toFloat(), textPaint)

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

    fun setViewText(newText: String) {
        this.text = newText
        invalidate()
    }

    fun setViewScaleX(scaleX: Int) {
        textPaint.textScaleX = scaleX.toFloat()
        invalidate()
    }

    fun setViewSpacing(spacing: Int) {
        textPaint.letterSpacing = spacing.toFloat()
        invalidate()
    }

    fun setViewSkew(skew: Int) {
        textPaint.textSkewX = skew.toFloat()
        invalidate()
    }

    fun setViewSize(size: Int) {
        textPaint.textSize = toDp(size.toFloat()).toFloat()
        invalidate()
    }

    fun setAlignment(position: Int) {
        when (position) {
            0 -> {
                align = Paint.Align.CENTER
            }
            1 -> {
                align = Paint.Align.LEFT
            }
            2 -> {
                align = Paint.Align.RIGHT
            }
        }

        invalidate()
    }

    fun setViewTextStyle(fillStyle: Paint.Style) {
        textPaint.style = fillStyle
        invalidate()
    }

    fun setViewTypeface(typeface: Typeface) {
        textPaint.typeface = typeface
        invalidate()
    }
}
