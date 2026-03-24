package com.example.colorgridgame

import android.graphics.Color
import android.os.Bundle
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
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
            cell.tag = cell.currentColorIndex()

            cell.setOnClickListener {
                changeColor(cell)
                checkWin()
            }

            cells.add(cell)
            grid.addView(cell)
        }
    }

    private fun randomColor(): Int {
        return colors[Random.nextInt(colors.size)]
    }

    private fun TextView.currentColorIndex(): Int {
        return colors.indexOf((this.background as? android.graphics.drawable.ColorDrawable)?.color
            ?: Color.RED)
    }

    private fun changeColor(cell: TextView) {
        val currentColor = (cell.background as android.graphics.drawable.ColorDrawable).color
        val index = colors.indexOf(currentColor)
        val nextIndex = (index + 1) % colors.size

        cell.setBackgroundColor(colors[nextIndex])
    }

    private fun checkWin() {
        val firstColor = (cells[0].background as android.graphics.drawable.ColorDrawable).color

        val win = cells.all {
            (it.background as android.graphics.drawable.ColorDrawable).color == firstColor
        }

        if (win) {
            showWinDialog()
        }
    }

    private fun showWinDialog() {
        AlertDialog.Builder(this)
            .setTitle("Перемога!")
            .setMessage("Усі квадрати одного кольору ")
            .setCancelable(false)
            .setPositiveButton("Restart") { _, _ ->
                createGrid()
            }
            .show()
    }
}