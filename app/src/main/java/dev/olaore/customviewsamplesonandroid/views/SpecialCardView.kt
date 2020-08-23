package dev.olaore.customviewsamplesonandroid.views

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import dev.olaore.customviewsamplesonandroid.R

class SpecialCardView @JvmOverloads
    constructor(
        private val ctx: Context, private val attributeSet: AttributeSet? = null, private val defStyleAttr: Int = 0
    ) : View(ctx, attributeSet, defStyleAttr) {

//    colors
    private var cardColor = Color.parseColor("#F3C06F")
    private var primaryTextColor = Color.BLACK
    private var secondaryTextColor = Color.GREEN

//    image resources
    private var imageResourceUri = R.drawable.ic_check_circle_black_24dp
    private lateinit var imageDrawable: Drawable

    //    metric values
    private var mediumTextSize = toDP(15f)
    private var bigTextSize = toDP(40f)
    private var secondaryTextSize = toDP(25f)
    private var rectWidth = 0f
    private var rectHeight = 0f

//    paint objects
    private var cardPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = cardColor }
    private var primaryTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = primaryTextColor
        textAlign = Paint.Align.LEFT
        textSize = mediumTextSize
    }
    private var bigTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = primaryTextColor
        textAlign = Paint.Align.RIGHT
        textSize = bigTextSize
    }
    private var secondaryTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = secondaryTextColor
        textAlign = Paint.Align.RIGHT
        textSize = secondaryTextSize
    }
    private var drawablePaint = Paint(Paint.ANTI_ALIAS_FLAG)

//    text content
    private var secondaryText = "Ayetoro Town"
    private var primaryTextOne = "Mr. Olaore Fouad"
    private var primaryTextTwo = "Balance"
    private var primaryTextThree = "$790"

    private var placeholderRect = Rect()

    private var viewHeight = 0
    private var viewWidth = 0

    private var lastHeight = 0

    init {
        preComputeValues()
        elevation = 10f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawCard(canvas)
        drawImage(canvas)
        drawSecondaryText(canvas)
        drawDetailsText(canvas, primaryTextOne, 0)
        drawDetailsText(canvas, primaryTextTwo, placeholderRect.height())

        lastHeight = placeholderRect.height()
        drawBigDetailsText(canvas, primaryTextThree, lastHeight)

    }

    private fun drawCard(canvas: Canvas?) {
        rectWidth = viewWidth - 20f
        canvas?.drawRoundRect(20f, 20f, rectWidth, rectHeight, 20f, 20f, cardPaint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewHeight = h
        viewWidth = w
    }

    private fun drawImage(canvas: Canvas?) {
        canvas?.let {
            imageDrawable.setBounds(
                (viewWidth - 20f - 20f - 80f).toInt(),
                (20f + 10f).toInt(),
                (viewWidth - 20f - 20f).toInt(),
                (20f + 10f + 80f).toInt()
            )
            imageDrawable.draw(it)
        }
    }

    private fun drawSecondaryText(canvas: Canvas?) {
        canvas?.let {
            secondaryTextPaint.getTextBounds(secondaryText, 0, secondaryText.length, placeholderRect)
            it.drawText(
                secondaryText,
                0,
                secondaryText.length,
                (viewWidth - 20f - 20f).toInt().toFloat(),
                placeholderRect.height() + 120f,
                secondaryTextPaint
            )
        }
    }

    private fun drawDetailsText(canvas: Canvas?, text: String, fromTop: Int) {
        canvas?.let {
            primaryTextPaint.getTextBounds(text, 0, text.length, placeholderRect)
            it.drawText(
                text,
                0,
                text.length,
                40f,
                placeholderRect.height() + 120f + mediumTextSize + 20f + fromTop.toFloat(),
                primaryTextPaint
            )
        }
    }

    private fun drawBigDetailsText(canvas: Canvas?, text: String, fromTop: Int) {
        canvas?.let {
            bigTextPaint.getTextBounds(text, 0, text.length, placeholderRect)
            it.drawText(
                text,
                0,
                text.length,
                (40f + placeholderRect.width()).toFloat(),
                placeholderRect.height() + 120f + bigTextSize + 20f + fromTop.toFloat(),
                bigTextPaint
            )
        }
    }

    private fun preComputeValues() {

        var finalCardHeight = 0f

        secondaryTextPaint.getTextBounds(secondaryText, 0, secondaryText.length, placeholderRect)
        finalCardHeight += placeholderRect.height()

        primaryTextPaint.getTextBounds(primaryTextOne, 0, primaryTextOne.length, placeholderRect)
        finalCardHeight += placeholderRect.height()

        primaryTextPaint.getTextBounds(primaryTextTwo, 0, primaryTextTwo.length, placeholderRect)
        finalCardHeight += placeholderRect.height()

        primaryTextPaint.getTextBounds(primaryTextThree, 0, primaryTextThree.length, placeholderRect)
        finalCardHeight += placeholderRect.height() * 2

        rectHeight = finalCardHeight * 10
        imageDrawable = ContextCompat.getDrawable(ctx, imageResourceUri)!!
    }

    private fun toDP(value: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value,
            resources.displayMetrics
        )
    }

}