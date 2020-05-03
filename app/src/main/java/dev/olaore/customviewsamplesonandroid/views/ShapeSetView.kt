package dev.olaore.customviewsamplesonandroid.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import dev.olaore.customviewsamplesonandroid.R

class ShapeSetView @JvmOverloads
    constructor(private val ctx: Context, private val attributeSet: AttributeSet? = null, defStyle: Int = 0)
    : View(ctx, attributeSet, defStyle) {

    private val rectangle: Rect
    private var rectWidth = 0
    private var rectHeight = 0

    private var halfRectWidth = 0
    private var halfRectHeight = 0

    private var paint = Paint().apply {
        this.isAntiAlias = true
        this.color = Color.BLUE
    }

    init {
        rectWidth = 200
        rectHeight = 200

        halfRectHeight = (rectHeight / 2)
        halfRectWidth = (rectWidth / 2)

        rectangle = Rect(
            rectWidth / 2, rectHeight / 2, rectWidth, rectHeight
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            it.drawRect(rectangle, paint)
        }
    }

}
