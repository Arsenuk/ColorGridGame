package com.example.colorgridgame

import android.graphics.Color
import android.widget.TextView
import kotlin.random.Random

class GameManager(
    private val onWin: () -> Unit
) {

    private val colors = listOf(Color.RED, Color.YELLOW, Color.GREEN)

    var level: Level = Level1()

    fun randomColor(): Int {
        return colors[Random.nextInt(colors.size)]
    }

    fun nextColor(current: Int): Int {
        val index = colors.indexOf(current)
        return colors[(index + 1) % colors.size]
    }

    fun extractColors(cells: List<TextView>): List<Int> {
        return cells.map {
            (it.background as android.graphics.drawable.ColorDrawable).color
        }
    }

    fun checkWin(cells: List<TextView>) {
        val data = extractColors(cells)
        if (level.checkWin(data)) {
            onWin()
        }
    }
}