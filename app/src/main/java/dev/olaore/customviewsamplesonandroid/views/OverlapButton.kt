package dev.olaore.customviewsamplesonandroid.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.marginTop
import dev.olaore.customviewsamplesonandroid.R
import android.view.ViewGroup.MarginLayoutParams




/**
 * TODO: document your custom view class.
 */
@SuppressLint("AppCompatCustomView")
class OverlapButton @JvmOverloads
constructor(
    private val ctx: Context,
    private val attributeSet: AttributeSet? = null,
    private val defStyleAttr: Int = 0,
    private val defStyleRes: Int = 0
) : AppCompatButton(ctx, attributeSet, defStyleAttr) {

    private val borderColor = "#000"
    private val borderPaint = Paint().apply {
        isAntiAlias = true
        color = Color.parseColor(borderColor)
        style = Paint.Style.STROKE
        strokeWidth = 4f
    }

    init {
        setBackgroundColor(Color.TRANSPARENT)
        text = "Fouad"
        textAlignment = TEXT_ALIGNMENT_CENTER
        setPadding(50, 50, 50, 50)
        setMargins()
    }

    override fun onDraw(canvas: Canvas?) {
        drawBorder(canvas)
        canvas?.drawColor(Color.RED)
        canvas?.drawCircle(
            width / 2f, height / 2f, 200f, borderPaint
        )

        super.onDraw(canvas)
    }

    private fun drawBorder(canvas: Canvas?) {
        canvas?.let {
            val roundRect = RectF(-10f, -10f, width - 10f, height - 10f)
            it.drawRoundRect(roundRect, 8f, 8f, borderPaint)
        }
    }

    private fun setMargins() {
        val marginParams = MarginLayoutParams(layoutParams)
        marginParams.setMargins(30, 30, 30, 30)
        layoutParams = marginParams
    }


}