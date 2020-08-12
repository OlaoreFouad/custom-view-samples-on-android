package dev.olaore.customviewsamplesonandroid.views

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import dev.olaore.customviewsamplesonandroid.R

class ResizedBitmapView @JvmOverloads
    constructor(
        private val ctx: Context, private val attributeSet: AttributeSet? = null, private val defStyleAttr: Int = 0
    ) : View(ctx, attributeSet, defStyleAttr) {

    private fun decodeScaledBitmapFromResource(
        res: Resources,
        resId: Int,
        reqWidth: Int,
        reqHeight: Int
    ) : Bitmap {

        return BitmapFactory.Options().run {
            inJustDecodeBounds = true
            BitmapFactory.decodeResource(res, resId, this)

            inSampleSize = calculateInSampleSize(this, reqHeight, reqWidth)
            Log.d("ResizedBitmapView", "inSampleSize: $inSampleSize")
            inJustDecodeBounds = false
            BitmapFactory.decodeResource(res, resId, this)
        }

    }

    private fun calculateInSampleSize(
            options: BitmapFactory.Options, reqHeight: Int, reqWidth: Int
    ): Int {

        val (height, width) = options.run { outHeight to outWidth }
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {

            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / reqHeight >= inSampleSize && halfWidth / reqWidth >= inSampleSize) {
                inSampleSize *= 2
            }

        }

        return inSampleSize

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(
            decodeScaledBitmapFromResource(resources, R.drawable.image, 200, 200), 10f, 10f, Paint()
        )
    }

}