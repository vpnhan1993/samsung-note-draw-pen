package com.jv.samsungnotedrawpen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.skydoves.colorpickerview.ColorEnvelope
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val board = findViewById<DrawingBoard>(R.id.board)
        val colorPalette = findViewById<ImageButton>(R.id.btn_color_palette)
        val eraser = findViewById<ImageButton>(R.id.btn_eraser)

        colorPalette.setOnClickListener {
            ColorPickerDialog.Builder(this).setTitle("Pick Color")
                .setPreferenceName("Color picker")
                .setPositiveButton("pick", object : ColorEnvelopeListener {
                    override fun onColorSelected(envelope: ColorEnvelope?, fromUser: Boolean) {
                        envelope?.let { board.color = it.color }
                    }
                })
                .setNegativeButton("cancel") { d, _ -> d.dismiss() }
                .attachAlphaSlideBar(true)
                .attachBrightnessSlideBar(true)
                .setBottomSpace(12)
                .show()
        }

        eraser.setOnClickListener { board.setEraser() }
    }
}