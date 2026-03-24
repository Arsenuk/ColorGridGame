package com.example.colorgridgame

import android.os.Bundle
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var grid: GridLayout
    private val cells = mutableListOf<TextView>()

    private lateinit var game: GameManager

    private var currentLevel = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        grid = findViewById(R.id.grid)

        game = GameManager {
            showWinDialog()
        }

        startLevel1()
    }
    

    private fun startLevel1() {
        currentLevel = 1
        game.level = Level1()
        createGrid()
    }

    private fun startLevel2() {
        currentLevel = 2
        game.level = Level2()
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
            cell.setBackgroundColor(game.randomColor())

            cell.setOnClickListener {
                val current = (cell.background as android.graphics.drawable.ColorDrawable).color
                cell.setBackgroundColor(game.nextColor(current))

                game.checkWin(cells)
            }

            cells.add(cell)
            grid.addView(cell)
        }
    }


    private fun showWinDialog() {

        val builder = AlertDialog.Builder(this)
            .setTitle("Перемога!")
            .setMessage("Рівень $currentLevel пройдено 🎉")
            .setCancelable(false)
            .setPositiveButton("Restart") { _, _ ->
                startLevel1()
            }
            .setNegativeButton("Exit") { _, _ ->
                finish()
            }

        if (currentLevel == 1) {
            builder.setNeutralButton("Next Level") { _, _ ->
                startLevel2()
            }
        }

        builder.show()
    }
}