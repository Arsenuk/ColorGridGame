package com.example.colorgridgame

import android.graphics.Color

class Level3 : Level {

    override fun checkWin(cells: List<Int>): Boolean {

        for (row in 0 until 5) {

            var redCount = 0

            for (col in 0 until 3) {
                val color = cells[row * 3 + col]
                if (color == Color.RED) {
                    redCount++
                }
            }


            if (redCount != 1) return false
        }

        return true
    }
}