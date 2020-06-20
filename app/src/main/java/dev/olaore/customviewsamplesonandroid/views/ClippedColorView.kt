package dev.olaore.customviewsamplesonandroid.views

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.TextPaint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.annotation.RequiresApi
import dev.olaore.customviewsamplesonandroid.R

class ClippedColorView @JvmOverloads
    constructor(private val ctx: Context, private val attributeSet: AttributeSet? = null, private val defStyleAttr: Int = 0)
    : View(ctx, attributeSet, defStyleAttr) {

//    color variables
    private var colorPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var color = Color.RED
    private var colorRect: Rect? = null
    private var heightPct = 0.7

//    path variables
    private var colorPath = Path()

//    text variables
    private var clippedColorViewTextColor = Color.WHITE
    private var headingTextSize = toDP(30f)
    private var subHeadingTextSize = toDP(20f)

    private var textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = headingTextSize
        color = clippedColorViewTextColor
    }

    init {
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

//        drawRect(canvas)
        drawPath(canvas)
    }

    private fun drawRect(canvas: Canvas?) {
        colorRect = Rect(
            0, 0, width, (heightPct * height).toInt()
        )
        colorPaint.color = this.color
        canvas?.drawRect(colorRect!!, colorPaint)
    }

    private fun drawPath(canvas: Canvas?) {
        // move to the starting point
        colorPath.moveTo((0.25 * width).toFloat(), 0f)

        // draw to mid screen
        colorPath.lineTo((0.25 * width).toFloat(), (0.5 * height).toFloat())
        colorPath.quadTo((0.5 * width).toFloat(), (0.7 * height).toFloat(), (0.75 * width).toFloat(), (0.5 * height).toFloat())
        colorPath.lineTo((0.75 * width).toFloat(), 0f)

        colorPaint.color = Color.BLACK
        colorPaint.strokeWidth = 4f

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            canvas?.clipPath(colorPath)
            canvas?.drawColor(Color.RED)
        } else {
            canvas?.clipOutPath(colorPath)
            canvas?.drawColor(Color.RED)
        }

//        colorPaint.style = Paint.Style.STROKE

    }

    private fun toDP(value: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, ctx.resources.displayMetrics)
    }

}