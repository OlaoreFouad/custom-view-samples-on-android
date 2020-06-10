package dev.olaore.customviewsamplesonandroid

import android.graphics.Paint
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text_to_draw.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                var text = p0.toString()
                my_text_view.setViewText(text)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        size_seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                var newSize = p1
                my_text_view.setViewSize(newSize)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

        skew_seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                var newSkewValue = p1
                my_text_view.setViewSkew(newSkewValue)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        spacing_seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                var spacingValue = p1
                my_text_view.setViewSpacing(spacingValue)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        scale_seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                var scaleValue = p1
                my_text_view.setViewScaleX(scaleValue)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        text_alignment_radio_group.setOnCheckedChangeListener { radioGroup, _ ->
            when (radioGroup.checkedRadioButtonId) {
                R.id.center_align -> my_text_view.setAlignment(0)
                R.id.left_align -> my_text_view.setAlignment(1)
                R.id.right_align -> my_text_view.setAlignment(2)
            }
        }

        text_style_radio_group.setOnCheckedChangeListener { radioGroup, _ ->
            when(radioGroup.checkedRadioButtonId) {
                R.id.fill_style -> my_text_view.setViewTextStyle(Paint.Style.FILL)
                R.id.stroke_style -> my_text_view.setViewTextStyle(Paint.Style.STROKE)
                R.id.fill_and_stroke -> my_text_view.setViewTextStyle(Paint.Style.FILL_AND_STROKE)
            }
        }

        typeface_radio_group.setOnCheckedChangeListener { radioGroup, _ ->
            when (radioGroup.checkedRadioButtonId) {
                R.id.default_typeface -> my_text_view.setViewTypeface(Typeface.DEFAULT)
                R.id.default_bold_typeface -> my_text_view.setViewTypeface(Typeface.DEFAULT_BOLD)
                R.id.monospace_typeface -> my_text_view.setViewTypeface(Typeface.MONOSPACE)
                R.id.sans_serif_typeface -> my_text_view.setViewTypeface(Typeface.SANS_SERIF)
                R.id.serif_typeface -> my_text_view.setViewTypeface(Typeface.SERIF)
                R.id.custom_typeface -> my_text_view.setViewTypeface(Typeface.createFromAsset(baseContext.assets, "fonts/raleway.ttf"))
            }
        }

        bold_switch.setOnCheckedChangeListener { _ , b ->
            my_text_view.setFontViewStyle(0, b)
        }

        underline.setOnCheckedChangeListener { _ , b ->
            my_text_view.setFontViewStyle(1, b)
        }

        strikethrough_switch.setOnCheckedChangeListener { _ , b ->
            my_text_view.setFontViewStyle(2, b)
        }

    }
}
