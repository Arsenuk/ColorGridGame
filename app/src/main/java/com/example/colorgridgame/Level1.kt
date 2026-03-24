package com.example.colorgridgame

class Level1 : Level {

    override fun checkWin(cells: List<Int>): Boolean {
        return cells.all { it == cells[0] }
    }
}