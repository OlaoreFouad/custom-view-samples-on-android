package dev.olaore.customviewsamplesonandroid.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import dev.olaore.customviewsamplesonandroid.R


class ArcedArrowView @JvmOverloads
    constructor(private val ctx: Context, private val attributeSet: AttributeSet? = null, private val defStyleAttr: Int = 0)
    : View(ctx, attributeSet, defStyleAttr) {

    private var angleCount = 1
    private var containerColorIndex = 0

    private var containerColors = listOf(
        Color.parseColor("#006100"), Color.parseColor("#722f37"), Color.parseColor("#593E31"), Color.parseColor("#394249")
    )
    private var colorTransparentBlack = "#80000000"
    private var arrowDrawable: Drawable? = null
    private var backArrowDrawable: Drawable? = null

    private var containerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = containerColors[0] }
    private var arcPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        style = Paint.Style.STROKE
        strokeWidth = 2f
    }
    private var backArrowPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.parseColor(colorTransparentBlack) }
    private var grayCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GRAY
        style = Paint.Style.STROKE
    }
    private var arrowPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        draw container with color
        drawContainer(canvas)

//        draw round background for arrow
        drawArrowBg(canvas)

//        draw arrow drawable itself on white background
        drawArrowImage(canvas)

//        draw outlined gray circle indicating path to tread for arc
        drawGrayCircle(canvas)

//        draw white arc itself
        drawArc(canvas)

        if (containerColorIndex != 0 && angleCount != 1) { // if we're not on first screen (angle is 1 part, and color index of container is 0), draw back
//            arrow
//        draw back arrow background
            drawBackArrowBackground(canvas)

//        draw back arrow image
            drawBackArrowImage(canvas)
        }

    }

    private fun drawArc(canvas: Canvas?) {

        var radius = 0.15f * width

        canvas?.drawArc(
            (width / 2).toFloat() - radius,
            ((0.8 * height) - radius).toFloat(),
            (width / 2).toFloat() + radius,
            ((0.8 * height) + radius).toFloat(),
            -90f, (angleCount * 90f),
            false,
            arcPaint
        )
    }

    private fun drawArrowBg(canvas: Canvas?) {
        canvas?.let {
            it.drawCircle(
                (width / 2).toFloat(), 0.8f * height, 0.1f * width, arrowPaint
            )
        }
    }

    private fun drawGrayCircle(canvas: Canvas?) {
        grayCirclePaint.strokeWidth = 2f
        canvas?.let {
            it.drawCircle(
                (width / 2).toFloat(), 0.8f * height, 0.15f * width, grayCirclePaint
            )
        }
    }

    private fun drawArrowImage(canvas: Canvas?) {

        var arrowX = (width / 2).toFloat()
        var arrowY = (.8f * height)

        var saveCount = canvas?.save()

        canvas?.translate(arrowX, arrowY)
        arrowDrawable = resources.getDrawable(R.drawable.next_arrow, null)
        arrowDrawable?.setBounds(-40, -40, 40, 40)
        arrowDrawable?.draw(canvas!!)
        canvas?.restoreToCount(saveCount!!)
    }

    private fun drawBackArrowBackground(canvas: Canvas?) {
        canvas?.let {
            it.drawRoundRect(
                60f, 60f, 160f, 160f, 20f, 20f, backArrowPaint
            )
        }
    }

    private fun drawBackArrowImage(canvas: Canvas?) {
        canvas?.let {
            val saveCount = it.save()

            it.translate(110f, 110f)
            backArrowDrawable = resources.getDrawable(R.drawable.back_arrow, null)
            backArrowDrawable?.setBounds(-35, -35, 35, 35)
            backArrowDrawable?.draw(it)

            it.restoreToCount(saveCount)
        }
    }

    private fun drawContainer(canvas: Canvas?) {
        containerPaint.color = containerColors[containerColorIndex]
        canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), containerPaint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action) {
            MotionEvent.ACTION_DOWN -> {
                val eventX = event.x
                val eventY = event.y

                if (eventX > 160f) { // next arrow is clicked
                    var buttonRadius = (0.1f * width)

                    val buttonX = (width / 2).toFloat() - buttonRadius
                    val buttonY = (0.8f * height) - buttonRadius
                    val buttonHeight = buttonY + (buttonRadius.times(2))
                    val buttonWidth = buttonX + (buttonRadius.times(2))

                    if ((eventX > buttonX && eventX < (buttonWidth)) && (eventY > buttonY && eventY < (buttonHeight))) {

                        when (containerColorIndex) {
                            containerColors.size - 1 -> {
                                containerColorIndex = 0
                            }
                            else -> containerColorIndex++
                        }

                        when (angleCount) {
                            4 -> {
                                angleCount = 1
                            }
                            else -> {
                                angleCount++
                            }
                        }
                        invalidate()
                    }

                } else { // back arrow is clicked

                    if ((eventX > 60f && eventX < 160f) && (eventY > 60f && eventY < 160f)) {
                        containerColorIndex--
                        angleCount--

                        invalidate()
                    }

                }


            }
        }

        return true
    }

}

