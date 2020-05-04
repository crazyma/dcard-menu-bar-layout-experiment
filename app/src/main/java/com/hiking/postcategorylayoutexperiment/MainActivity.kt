package com.hiking.postcategorylayoutexperiment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.core.view.updateLayoutParams
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forumButton.text = forumEditText.text
        forumEditText.doOnTextChanged { text, _, _, _ ->
            forumButton.text = text.toString()
            calcSize()
        }
        categoryButton.text = categoryEditText.text
        categoryEditText.doOnTextChanged { text, _, _, _ ->
            categoryButton.text = text.toString()
            calcSize()
        }
        reservedWidthSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                categoryReservedSpace.updateLayoutParams {
                    width = (progress * resources.displayMetrics.density).roundToInt()
                }
                reservedWidthDebugTextView.text = "${progress}dp"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        calcSize()
    }

    private fun calcSize() {
        val density = resources.displayMetrics.density
        forumButton.post {
            forumDebugTextView.text = "${"%.2f".format(forumButton.measuredWidth / density)}dp"
        }
        categoryButton.post {
            categoryDebugTextView.text =
                "${"%.2f".format(categoryButton.measuredWidth / density)}dp"
        }
    }
}
