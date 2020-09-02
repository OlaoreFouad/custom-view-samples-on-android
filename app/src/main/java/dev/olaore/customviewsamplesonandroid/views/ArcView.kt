package dev.olaore.customviewsamplesonandroid.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import dev.olaore.customviewsamplesonandroid.R
import kotlin.math.round

/**
 * TODO: document your custom view class.
 */
class ArcView @JvmOverloads
    constructor(private val ctx: Context, private val attributeSet: AttributeSet? = null, private val defStyleAttr: Int = 0)
    : View(ctx, attributeSet, defStyleAttr) {

    private val arcPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#F7C500")
        strokeWidth = 5f
    }

    var xStart = 0
    var yStart = 0
    var foodWidth = 10
    var spacing = 20f
    var amount = 0

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawArc(canvas)
        xStart = width / 2
        yStart = height / 2
        drawFood(canvas)
        drawEye(canvas)
    }

    private fun drawArc(canvas: Canvas?) {
        canvas?.let {
            it.drawArc(0f, 0f, width.toFloat() - 5f, height.toFloat() - 5f, 25f, 290f, true, arcPaint)
        }
    }

    private fun drawEye(canvas: Canvas?) {
        var eyeX = 0.6 * width
        var eyeY = 0.25 * height
        canvas?.let {
            it.drawCircle(
                eyeX.toFloat(), eyeY.toFloat(), 40f, Paint(Paint.ANTI_ALIAS_FLAG).apply {
                    color = Color.WHITE
                }
            )
        }
    }

    private fun drawFood(canvas: Canvas?) {
        canvas?.let {
            xStart += spacing.toInt()
            amount = (xStart / foodWidth).toInt()
            amount /= 2
            for (cnt in 0 until amount) {
                xStart = (xStart + (spacing * (cnt + 1))).toInt()
                it.drawCircle(xStart.toFloat(), yStart.toFloat(), 20f, Paint(Paint.ANTI_ALIAS_FLAG).apply {
                    color = Color.BLACK
                })
            }
        }
    }

}