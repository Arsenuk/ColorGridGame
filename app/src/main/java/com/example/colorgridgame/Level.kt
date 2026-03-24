package com.example.colorgridgame

interface Level {
    fun checkWin(cells: List<Int>): Boolean
}