package com.example.rich.tictactoe

interface TicTacToeView {
    fun reset()
    fun setButtonImage(x: Int, y: Int, player: Model.Player)
    fun showResult(result: Model.Player)
}