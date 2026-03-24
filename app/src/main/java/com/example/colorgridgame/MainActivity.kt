package com.example.colorgridgame

import android.graphics.Color
import android.os.Bundle
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var grid: GridLayout
    private val cells = mutableListOf<TextView>()

    private val colors = listOf(
        Color.RED,
        Color.YELLOW,
        Color.GREEN
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        grid = findViewById(R.id.grid)

        createGrid()
    }

    private fun createGrid() {
        grid.removeAllViews()
        cells.clear()

        for (i in 0 until 15) {
            val cell = TextView(this)

            val params = GridLayout.LayoutParams().apply {
                width = 0
                height = 0
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                setMargins(8, 8, 8, 8)
            }

            cell.layoutParams = params

            cell.setBackgroundColor(randomColor())

            cells.add(cell)
            grid.addView(cell)
        }
    }

    private fun randomColor(): Int {
        return colors[Random.nextInt(colors.size)]
    }
}