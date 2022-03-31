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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.marginTop
import dev.olaore.customviewsamplesonandroid.R
import android.view.ViewGroup.MarginLayoutParams
import androidx.constraintlayout.widget.ConstraintLayout


/**
 * TODO: document your custom view class.
 */
@SuppressLint("AppCompatCustomView")
class OverlapButton2 @JvmOverloads
constructor(
    private val ctx: Context,
    private val attributeSet: AttributeSet? = null,
    private val defStyleAttr: Int = 0,
) : ConstraintLayout(ctx, attributeSet, defStyleAttr) {

    init {
        val layoutInflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        layoutInflater.inflate(R.layout.overlap_button, this)

        val border = findViewById<View>(R.id.hey)
        bringChildToFront(border)
    }

}