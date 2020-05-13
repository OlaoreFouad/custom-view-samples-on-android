package dev.olaore.customviewsamplesonandroid.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import dev.olaore.customviewsamplesonandroid.R

/**
 * TODO: document your custom view class.
 */
class IrregularBackgroundView @JvmOverloads
    constructor(val ctx: Context, val attributeSet: AttributeSet? = null, val defStyleAttr: Int = 0)
    : View(ctx, attributeSet, defStyleAttr) {

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GREEN
    }
    var path = Path()

    init {
    }

    var startX: Float = 0f
    var startY: Float = 0f

    var firstEdgeX: Float = 0f
    var firstEdgeY: Float = 0f

    var secondEdgeX: Float = 0f
    var secondEdgeY: Float = 0f

    var thirdEdgeX: Float = 0f
    var thirdEdgeY: Float = 0f

    var fourthEdgeX: Float = 0f
    var fourthEdgeY: Float = 0f

    var fifthEdgeX: Float = 0f
    var fifthEdgeY: Float = 0f

    var sixthEdgeX: Float = 0f
    var sixthEdgeY: Float = 0f

    var seventhEdgeX: Float = 0f
    var seventhEdgeY: Float = 0f

    var eighthEdgeX: Float = 0f
    var eighthEdgeY: Float = 0f

    var ninthEdgeX: Float = 0f
    var ninthEdgeY: Float = 0f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawIrregularPath(canvas)
    }

    private fun drawIrregularPath(canvas: Canvas?) {
        refreshValues()

        path.moveTo(startX, startY)
        path.lineTo(firstEdgeX, firstEdgeY)

        path.quadTo(0.1f * width, 0.85f * height, secondEdgeX, secondEdgeY)
        path.lineTo(thirdEdgeX, thirdEdgeY)
        path.quadTo(0.15f * width, 0.35f * height, fourthEdgeX, fourthEdgeY)
        path.quadTo(0.5f * width, 0.3f * height, fifthEdgeX, fifthEdgeY)
        path.quadTo(0.6f * width, 0.1f, sixthEdgeX, sixthEdgeY)
        path.lineTo(seventhEdgeX, seventhEdgeY)
        path.quadTo(eighthEdgeX, eighthEdgeY, startX, startY)

        canvas?.drawPath(path, paint)
    }

    private fun refreshValues() {
        startX = (0.5 * width).toFloat()
        startY = (0.9 * height).toFloat()

        firstEdgeX = (0.2 * width).toFloat()
        firstEdgeY = (0.9 * height).toFloat()

        secondEdgeX = (0.1 * width).toFloat()
        secondEdgeY = (0.8 * height).toFloat()

        thirdEdgeX = (0.1 * width).toFloat()
        thirdEdgeY = (0.5 * height).toFloat()

        fourthEdgeX = (0.2 * width).toFloat()
        fourthEdgeY = (0.4 * height).toFloat()

        fifthEdgeX = (0.7 * width).toFloat()
        fifthEdgeY = (0.2 * height).toFloat()

        sixthEdgeX = (0.8 * width).toFloat()
        sixthEdgeY = (0.2 * height).toFloat()

        seventhEdgeX = (0.8 * width).toFloat()
        seventhEdgeY = (0.8 * height).toFloat()

        eighthEdgeX = (0.7 * width).toFloat()
        eighthEdgeX = (0.9 * height).toFloat()
    }

}
