package com.example.colorgridgame

import android.graphics.Color

class Level4 : Level {

    override fun checkWin(cells: List<Int>): Boolean {

        val rows = 5
        val cols = 3

        fun index(r: Int, c: Int) = r * cols + c

        for (r in 0 until rows) {
            for (c in 0 until cols) {

                val current = cells[index(r, c)]

                // перевіряємо 8 напрямків (сусіди)
                for (dr in -1..1) {
                    for (dc in -1..1) {

                        if (dr == 0 && dc == 0) continue

                        val nr = r + dr
                        val nc = c + dc

                        if (nr in 0 until rows && nc in 0 until cols) {

                            val neighbor = cells[index(nr, nc)]

                            if (current == neighbor) return false
                        }
                    }
                }
            }
        }

        return true
    }
}