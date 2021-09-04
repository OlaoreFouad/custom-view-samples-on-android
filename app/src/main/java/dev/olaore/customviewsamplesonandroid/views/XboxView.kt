package dev.olaore.customviewsamplesonandroid.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import kotlin.math.sqrt

class XboxView @JvmOverloads
constructor(
    private val ctx: Context,
    private val attributeSet: AttributeSet? = null,
    private val defStyleAttr: Int = 0,
    private val defStyleRes: Int = 0
) : View(ctx, attributeSet, defStyleAttr, defStyleRes) {

    private val xboxColor: Int = Color.parseColor("#107C10")
    private val xboxPathPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = xboxColor
        style = Paint.Style.FILL
        strokeWidth = 1f
    }

    private val boundingRectPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 1f
    }
    private val boundingRectSize: Float = 0.5F
    private val boundingRectXOffsetPct: Float = 0.2F
    private val boundingRectYOffsetPct: Float = 0.3F

    private var globalRectWidth: Float = 0F


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

//        draw bounding rectangle
        drawBoundingRect(canvas)

//        draw bounding circle
        drawBoundingCircle(canvas)

//        draw top
        drawTop(canvas)

//        draw left
        drawLeft(canvas)

//        draw right
        drawRight(canvas)

//        draw bottom
        drawBottom(canvas)
    }

    private fun drawBoundingRect(canvas: Canvas?) {
        val xOffset = boundingRectXOffsetPct * width
        val yOffset = boundingRectYOffsetPct * height

        val rectWidth = (boundingRectSize - boundingRectXOffsetPct) * width * 2
        val saveCount = canvas?.save()

        canvas?.let {
            it.translate(xOffset, yOffset)
            it.drawRect(0f, 0f, rectWidth, rectWidth, boundingRectPaint)
            it.restoreToCount(saveCount!!)
        }

        globalRectWidth = rectWidth

    }

    private fun drawBoundingCircle(canvas: Canvas?) {

        val xOffset = boundingRectSize * width
        val yOffset = boundingRectYOffsetPct * height + (globalRectWidth / 2f)

        canvas?.let {
            it.translate(
                xOffset, yOffset
            )
            it.drawCircle(0f, 0f, globalRectWidth / 2f, boundingRectPaint)
        }

    }

    private fun drawTop(canvas: Canvas?) {

        val xOffset = boundingRectXOffsetPct * width
        val yOffset = boundingRectYOffsetPct * height

        canvas?.let {

            val saveCount = it.save()

            it.translate(-globalRectWidth / 2, -globalRectWidth / 2)
            it.translate(0.3f * globalRectWidth, 0.045f * globalRectWidth)

            val topPath = Path()

            topPath.moveTo(0f, 0f)
            topPath.rQuadTo(0.2f * globalRectWidth, -0.1f * globalRectWidth, 0.4f * globalRectWidth, 0f)
            topPath.rQuadTo(-0.2f * globalRectWidth, 0.1f * globalRectWidth, -0.2f * globalRectWidth, 0.2f * globalRectWidth)
            topPath.rQuadTo(0f, -0.1f * globalRectWidth, -0.2f * globalRectWidth, -0.2f * globalRectWidth)

            it.drawPath(topPath, xboxPathPaint)
            it.restoreToCount(saveCount)


        }

    }

    private fun drawLeft(canvas: Canvas?) {

        canvas?.let {

            val saveCount = it.save()

            it.translate(-globalRectWidth / 2, -globalRectWidth / 2)
            it.translate(0.14f * globalRectWidth, 0.15f * globalRectWidth)

            val leftPath = Path()

            leftPath.moveTo(0f, 0f)
            leftPath.rQuadTo(-0.3f * globalRectWidth, 0.35f * globalRectWidth, -0.012f * globalRectWidth, 0.68f * globalRectWidth)
            leftPath.rQuadTo(0.1f * globalRectWidth, -0.4f * globalRectWidth, 0.25f * globalRectWidth, -0.5f * globalRectWidth)
            leftPath.rQuadTo(-0.1f * globalRectWidth, -0.15f * globalRectWidth, -0.23f * globalRectWidth, -0.185f * globalRectWidth)

            it.drawPath(leftPath, xboxPathPaint)
            it.restoreToCount(saveCount)

        }

    }

    private fun drawRight(canvas: Canvas?) {
        canvas?.let {
            val saveCount = it.save()
            it.translate(globalRectWidth / 2, -globalRectWidth / 2)
            it.translate(-0.14f * globalRectWidth, 0.15f * globalRectWidth)

            val rightPath = Path()

            rightPath.moveTo(0f, 0f)
            rightPath.rQuadTo(0.3f * globalRectWidth, 0.35f * globalRectWidth, 0.012f * globalRectWidth, 0.68f * globalRectWidth)
            rightPath.rQuadTo(-0.1f * globalRectWidth, -0.4f * globalRectWidth, -0.25f * globalRectWidth, -0.5f * globalRectWidth)
            rightPath.rQuadTo(0.1f * globalRectWidth, -0.15f * globalRectWidth, 0.23f * globalRectWidth, -0.185f * globalRectWidth)

            it.drawPath(rightPath, xboxPathPaint)
            it.restoreToCount(saveCount)

        }
    }

    private fun drawBottom(canvas: Canvas?) {
        canvas?.let {

            it.translate(-globalRectWidth / 2, globalRectWidth / 2)
            it.translate(0.225f * globalRectWidth, -0.075f * globalRectWidth)

            val bottomPath = Path()

            bottomPath.moveTo(0f, 0f)
            bottomPath.rQuadTo(0.3f * globalRectWidth, 0.2f * globalRectWidth, 0.575f * globalRectWidth, -0.005f * globalRectWidth)
            bottomPath.rQuadTo(0.005f * globalRectWidth, -0.25f * globalRectWidth, -0.3f * globalRectWidth, -0.5f * globalRectWidth)
            bottomPath.rQuadTo(-0.3f * globalRectWidth, 0.25f * globalRectWidth, -0.275f * globalRectWidth, 0.5f * globalRectWidth)

            it.drawPath(bottomPath, xboxPathPaint)

        }
    }

    private fun toDP(value: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, value, ctx.resources.displayMetrics
        )
    }

}