package dev.olaore.customviewsamplesonandroid.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class ClippedView @JvmOverloads
    constructor(
        private val ctx: Context, private val attributeSet: AttributeSet? = null, private val defStyleAttr: Int = 0
    ) : View(ctx, attributeSet, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16f
        strokeWidth = 6f
        textAlign = Paint.Align.RIGHT
    }

    private val path = Path()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.GRAY)

        canvas?.save()
        canvas?.translate(10f, 10f)
        drawScene(canvas)
        canvas?.restore()

        canvas?.save()
        canvas?.translate(160f, 10f)
        canvas?.clipRect(10f, 10f, 90f, 90f)
        canvas?.clipRect(30f, 30f, 70f, 70f, Region.Op.DIFFERENCE)
        drawScene(canvas)
        canvas?.restore()

        canvas?.save();
        canvas?.translate(10f, 160f);
        path.reset();
        canvas?.clipPath(path); // makes the clip empty
        path.addCircle(50f, 50f, 50f, Path.Direction.CCW);
        canvas?.clipPath(path);
        drawScene(canvas);
        canvas?.restore();

    }

    private fun drawScene(canvas: Canvas?) {
        canvas?.let {
            it.clipRect(0, 0, 100, 100)
            it.drawColor(Color.WHITE)

            paint.color = Color.RED
            it.drawLine(0f, 0f, 100f,100f, paint)

            paint.color = Color.GREEN
            it.drawCircle(30f, 70f,30f, paint)

            paint.color = Color.BLUE
            it.drawText("Clipping", 100f, 30f, paint)
        }
    }

}