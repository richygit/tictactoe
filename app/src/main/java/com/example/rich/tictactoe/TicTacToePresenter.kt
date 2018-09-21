package com.example.rich.tictactoe

class TicTacToePresenter(private val model: Model, private val view: TicTacToeView): Presenter {

    override fun onCellClicked(x: Int, y: Int) {
        val moved= model.markCurrentPlayer(x, y) ?: return

        if (moved == Model.Player.CROSS) {
            view.setButtonImage(x, y, Model.Player.CROSS)
        } else {
            view.setButtonImage(x, y, Model.Player.CIRCLE)
        }
        val result = model.gameResult() ?: return
        view.showResult(result)
    }

    override fun onNext() {
        if (model.gameResult() != null) {
            model.reset()
            view.reset()
        }
    }
}