package com.example.colorgridgame

import android.graphics.Color

class Level2 : Level {

    private val target = listOf(
        Color.YELLOW,
        Color.RED,
        Color.GREEN
    )

    override fun checkWin(cells: List<Int>): Boolean {

        for (col in 0 until 3) {
            val columnColor = cells[col]
            if (columnColor != target[col]) return false

            for (row in 1 until 5) {
                if (cells[row * 3 + col] != columnColor) return false
            }
        }
        return true
    }
}