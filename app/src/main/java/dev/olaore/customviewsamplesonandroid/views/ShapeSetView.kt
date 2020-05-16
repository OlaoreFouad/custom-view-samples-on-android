package dev.olaore.customviewsamplesonandroid.views

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.CountDownTimer
import android.text.TextPaint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import dev.olaore.customviewsamplesonandroid.R

class ShapeSetView @JvmOverloads
    constructor(private val ctx: Context, private val attributeSet: AttributeSet? = null, defStyle: Int = 0)
    : View(ctx, attributeSet, defStyle) {

//    rectangle objects
    private var leftRect: Rect? = null
    private var rightRect: Rect? = null

//    rectangle dimensions
    private var rectWidth = 200
    private var rectHeight = 200

    private var mImage: Bitmap

//    values of the half values of the dimensions
    private var halfRectWidth = 0
    private var halfRectHeight = 0

//    circle parameters
    private var mCircleX = 0f
    private var mCircleY = 0f
    private var radius = 200

    private var clicked = 0

    private var paint = Paint().apply {
        this.isAntiAlias = true
        this.color = Color.BLUE
    }

    private var imageX = 0f
    private var imageY = 0f

    private var imagePadding = 50

    private var globalLayoutListener: ViewTreeObserver.OnGlobalLayoutListener? = null

    init {
        preComputeValues()

        mImage = BitmapFactory.decodeResource(resources, R.drawable.image2)

        globalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
            viewTreeObserver.removeOnGlobalLayoutListener(globalLayoutListener)
            mImage = getResizedImage(mImage, width.toFloat() - imagePadding, height.toFloat() - imageY)
        }

        /*
        *
        * */

        val c = object : CountDownTimer(2000L, 500L) {
            override fun onFinish() {
                println("timer: completed!")
            }

            override fun onTick(p0: Long) {
                val newWidth: Float = (mImage.width - 50).toFloat()
                val newHeight: Float = (mImage.height - 50).toFloat()

                mImage = getResizedImage(mImage, newWidth, newHeight)
                postInvalidate()
            }
        }
        c.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mImage = getResizedImage(mImage, width.toFloat(), height.toFloat())
        drawLeftRect(canvas)
        drawRightRect(canvas)

        if (mCircleX == 0f || mCircleY == 0f)
        {
            mCircleY = (height / 2).toFloat()
            mCircleX = (width / 2).toFloat()
        }

        drawCircle(canvas)

        imageX = (width - mImage.width) / 2f
        imageY = (height - mImage.height) / 2f

//        drawImage(canvas)
    }

    private fun preComputeValues() {
        halfRectWidth = rectWidth / 2
        halfRectHeight = rectHeight / 2
    }

    private fun drawLeftRect(canvas: Canvas?) {
        paint.color = Color.BLUE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f

        leftRect = Rect(
            halfRectWidth, halfRectHeight, rectWidth, rectHeight
        )

        canvas?.drawRect(leftRect!!, paint)
    }

    private fun drawRightRect(canvas: Canvas?) {
        paint.color = Color.RED
        paint.style = Paint.Style.FILL

        var leftOffSet = width - rectWidth
        var topOffSet = halfRectHeight
        var rightOffSet = width - halfRectWidth
        var bottomOffSet = rectHeight

        rightRect = Rect(
            leftOffSet, topOffSet, rightOffSet, bottomOffSet
        )

        canvas?.drawRect(rightRect!!, paint)
    }

    private fun drawCircle(canvas: Canvas?) {

//        canvas?.translate(width / 2f, height / 2f)
        paint.color = Color.GREEN

        canvas?.drawCircle(mCircleX, mCircleY, radius.toFloat(), paint)

    }

    private fun drawImage(canvas: Canvas?) {
        canvas?.drawBitmap(mImage, imageX, imageY, null)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        return when(event?.action) {
            MotionEvent.ACTION_DOWN -> {
                clicked = 1
                true
            }
            MotionEvent.ACTION_MOVE -> {

                var dx = Math.abs(event.x - mCircleX)
                var dy = Math.abs(event.y - mCircleY)

                if ((dx + dy) < radius) {
                    mCircleX = event.x
                    mCircleY = event.y

                    invalidate()
                    println("shapeSet: mCircleX: $mCircleX, mCircleY: $mCircleY")

                    return true
                }

                super.onTouchEvent(event)

            }
            else -> super.onTouchEvent(event)
        }
    }

    override fun performClick(): Boolean {
        println("shapeSet: click performed!, this gets called first:  ${ if (clicked == 0) "yes!" else "hell nah!" }")

        return true
    }

    private fun getResizedImage(bitmap: Bitmap, requiredWidth: Float, requiredHeight: Float): Bitmap {

        val matrix = Matrix()

        val srcRect = RectF(0f, 0f, bitmap.width.toFloat(), bitmap.height.toFloat())
        val destRect = RectF(0f, 0f, requiredWidth, requiredHeight)

        matrix.setRectToRect(srcRect, destRect, Matrix.ScaleToFit.CENTER)

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }



}

private fun ViewTreeObserver.removeOnGlobalFocusChangeListener(shapeSetView: ShapeSetView) {

}
