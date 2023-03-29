package com.jv.samsungnotedrawpen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val board = findViewById<DrawingBoard>(R.id.board)
        findViewById<Button>(R.id.eraser).setOnClickListener { board.setEraser() }
    }
}