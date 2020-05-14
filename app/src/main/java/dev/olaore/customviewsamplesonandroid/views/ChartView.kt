package dev.olaore.customviewsamplesonandroid.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import dev.olaore.customviewsamplesonandroid.R

/**
 * TODO: document your custom view class.
 */
class ChartView @JvmOverloads
    constructor(private val ctx: Context, private val attributeSet: AttributeSet? = null, private val defStyleAttr: Int = 0)
    : View(ctx, attributeSet, defStyleAttr) {

//    paint objects
    private var crossLinePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.BLACK }
    private var mainLinePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.DKGRAY }
    private var barPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.RED }

//    constants
    private val TAG = ChartView::class.java.simpleName

//    dimensions
    private var extraPadding = 10
    private var barWidth = 0f
    private var spacing = 0f

    private var totalWidth = 0f
    private var totalHeight = 0f

//    cross line dimens
    private var crossStartX = 0
    private var crossStartY = 0
    private var crossEndX = 0
    private var crossEndY = 0

//    data
    private var data = listOf(10, 20, 30, 40, 50, 60, 70, 70, 60, 80, 90)

    init {
        refreshValues()
    }

    private fun refreshValues() {
        setPadding(extraPadding, extraPadding, extraPadding, extraPadding)
        spacing = 10f
        barWidth = ((width - paddingLeft - paddingRight) / data.size).toFloat() - (spacing * 2)

        totalWidth = width + (extraPadding * 2).toFloat()
        totalHeight = height + (extraPadding * 2).toFloat()

        crossStartX = extraPadding + 10
        crossStartY = extraPadding + 10
        crossEndX = extraPadding + 10
        crossEndY = height - paddingTop - paddingBottom
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawCrossLine(canvas)
        drawBottomLine(canvas)
        drawMainLines(canvas)
        drawBars(canvas)
    }

    private fun drawCrossLine(canvas: Canvas?) {
        crossLinePaint.strokeWidth = 4f
        crossEndY = height - paddingTop - paddingBottom
        canvas?.let {
            it.drawLine(crossStartX.toFloat(), crossStartY.toFloat(), crossEndX.toFloat(), crossEndY.toFloat(), crossLinePaint)
        }
    }

    private fun drawBottomLine(canvas: Canvas?) {
        crossLinePaint.strokeWidth = 4f
        crossEndY = height - paddingTop - paddingBottom

        val bottomLineY = (height - paddingTop - paddingBottom).toFloat()

        canvas?.let {
            it.drawLine(
                crossStartX.toFloat(),
                bottomLineY,
                (width - paddingLeft - paddingRight).toFloat(),
                bottomLineY,
                crossLinePaint)
        }
    }

    private fun drawMainLines(canvas: Canvas?) {
        var startingPointX = crossStartX
        var startingPointY = crossStartY

        val endPointX = width - paddingLeft - paddingRight
        var endPointY = crossStartY

        mainLinePaint.strokeWidth = 2f
        canvas?.let {

            val spacing = (height - paddingTop - paddingBottom) / 10
            for (i in 0..10) {
                it.drawLine(startingPointX.toFloat(), startingPointY.toFloat(), endPointX.toFloat(), startingPointY.toFloat(), mainLinePaint)

                startingPointY += spacing
            }

        }

    }

    private fun drawBars(canvas: Canvas?) {

        barWidth = ((width - paddingLeft - paddingRight) / data.size).toFloat() - (spacing * 2)
        Log.d(TAG, "Bar Width: $barWidth, Data: ${ data.size }, Spacing: $spacing")

        canvas?.let {

            var saveCount = it.save()
            data.forEachIndexed { index, number ->

                val barValue = (number.toFloat() / 100f) * (height - paddingTop - paddingBottom)
                val startY = height - paddingTop - paddingBottom - barValue
                Log.d(TAG, "number: $number, bar value: $barValue, height: $height, startY: $startY")

                var startX = 0f

                if (index == 0) {
                    startX = (paddingLeft + 10 + spacing) + spacing
                    it.translate(startX, startY)

                    Log.d(TAG, "paddingTop: $paddingTop, paddingBottom: $paddingBottom")
                    it.drawRect(0f, 0f, barWidth, barValue, barPaint)
                } else {
                    startX = (paddingLeft + 10 + (barWidth * index) + (spacing * (index + 1))) + spacing
                    it.translate(startX, startY)

                    it.drawRect(0f, 0f, barWidth, barValue, barPaint)
                }

                it.translate(-startX, -startY)

            }
            it.restoreToCount(saveCount)

        }

    }

}
