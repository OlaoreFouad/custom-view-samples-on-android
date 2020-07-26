package dev.olaore.customviewsamplesonandroid.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import dev.olaore.customviewsamplesonandroid.R
import java.util.*

/**
 * TODO: document your custom view class.
 */
class RepeatedRectView @JvmOverloads
    constructor(
        private val ctx: Context, private val attributeSet: AttributeSet? = null, private val defStyleAttr: Int = 0
    ) : View(ctx, attributeSet, defStyleAttr) {

    val colors = listOf(Color.YELLOW, Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.BLACK, Color.DKGRAY, Color.GRAY, Color.CYAN)
    var rectInset = 10
    var rectAmount = 0
    var rectWidth = 0f
    var rectHeight = 0f
    var rectPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        rectAmount = (width / 2) / rectInset
        canvas?.translate(0f, 0f)
        for (i in 1 until rectAmount) {
            rectWidth = width - ((i - 1) * rectInset.toFloat() * 2)
            rectHeight = height - ((i - 1) * rectInset.toFloat() * 2)
            drawRect(canvas)
            canvas?.translate(rectInset.toFloat(), rectInset.toFloat())
        }
    }

    private fun drawRect(canvas: Canvas?) {
        val randomNumber = Random().nextInt(colors.size)
        rectPaint.color = colors[randomNumber]
        canvas?.drawRect(0f, 0f, rectWidth, rectHeight, rectPaint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                invalidate()
            }
        }
        return super.onTouchEvent(event)
    }

}