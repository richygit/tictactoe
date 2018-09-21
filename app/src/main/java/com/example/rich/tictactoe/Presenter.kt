package com.example.rich.tictactoe

interface Presenter {
    fun onCellClicked(x: Int, y: Int)
    fun onNext()
}