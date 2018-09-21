package com.example.rich.tictactoe

import android.util.Log

class Model {
    enum class Player {CROSS, CIRCLE, NONE}

    lateinit var currentPlayer: Player
    lateinit var gameState: Array<Array<Player>>

    init {
        reset()
    }

    fun reset() {
        currentPlayer = Player.CIRCLE
        gameState = Array(3) {Array(3) {Player.NONE} }
    }

    fun markCurrentPlayer(posn: String): Player? {
        val x = Character.getNumericValue(posn[0])-1
        val y = Character.getNumericValue(posn[1])-1
        if (gameState[x][y] == Player.NONE) {
            gameState[x][y] = currentPlayer
            Log.d("MODEL", "game[$x][$y] = $currentPlayer")
            val moved = currentPlayer
            switchPlayers()
            return moved
        } else {
            return null
        }
    }

    fun switchPlayers() {
        currentPlayer = if (currentPlayer == Player.CROSS) Player.CIRCLE else Player.CROSS
    }

    private fun winnerDiagonal(): Player? {
        if (gameState[0][0] == gameState[1][1] && gameState[1][1] == gameState[2][2] && gameState[1][1] != Player.NONE) {
            return gameState[1][1]
        }
        if (gameState[2][0] == gameState[1][1] && gameState[1][1] == gameState[0][2] && gameState[1][1] != Player.NONE) {
            return gameState[1][1]
        }
        return null
    }

    private fun winnerDown(): Player? {
        for (col in 0..2) {
            if (gameState[col][0] == gameState[col][1] && gameState[col][1] == gameState[col][2] && gameState[col][0] != Player.NONE) {
                return gameState[col][0]
            }
        }
        return null
    }

    private fun winnerAcross(): Player? {
        for (row in 0..2) {
            if (gameState[0][row] == gameState[1][row] && gameState[1][row] == gameState[2][row] && gameState[0][row] != Player.NONE) {
                return gameState[0][row]
            }
        }
        return null
    }

    private fun isDraw(): Boolean {
        for (row in 0..2) {
            for (col in 0..2) {
                if (gameState[row][col] == Player.NONE) {
                    return false
                }
            }
        }
        return true
    }

    fun gameResult(): Player? {
        if (winnerAcross() != null) {
            return winnerAcross()
        }
        if (winnerDown() != null) {
            return winnerDown()
        }
        if (winnerDiagonal() != null) {
            return winnerDiagonal()
        }
        if (isDraw()) {
            return Player.NONE
        }
        return null
    }
}
