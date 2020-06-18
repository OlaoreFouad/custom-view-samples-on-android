package dev.olaore.customviewsamplesonandroid.views

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import dev.olaore.customviewsamplesonandroid.R

/**
 * TODO: document your custom view class.
 */
class MyBitmapView @JvmOverloads
    constructor(
        private val ctx: Context,
        private val attributeSet: AttributeSet? = null,
        private val defStyleAttr: Int = 0
    )
    : View(ctx, attributeSet, defStyleAttr) {

    private var imageBitmap: Bitmap? = null

    init {
        imageBitmap = BitmapFactory.decodeResource(resources, R.drawable.image)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawImage(canvas)
    }

    private fun drawImage(canvas: Canvas?) {
        canvas?.let {
            it.drawBitmap(imageBitmap!!, null, RectF(0f, 0f, width.toFloat(), height.toFloat()), Paint(Paint.ANTI_ALIAS_FLAG))
        }
    }

}
