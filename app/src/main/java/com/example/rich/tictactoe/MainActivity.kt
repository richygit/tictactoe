package com.example.rich.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.find
import org.jetbrains.anko.yesButton

class MainActivity : AppCompatActivity(), TicTacToeView {

    private val presenter: Presenter = TicTacToePresenter(Model(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onNext(view: View) {
        presenter.onNext()
    }

    fun onClick(view: View) {
        val stringId = resources.getResourceEntryName(view.id).substring(3)
        val x = Character.getNumericValue(stringId[0])-1
        val y = Character.getNumericValue(stringId[1])-1
        presenter.onCellClicked(x, y)
    }

    override fun reset() {
        Log.d("MAIN", "childcount = " + tableLayout.childCount)
        for (i in 0..tableLayout.childCount) {
            val row = tableLayout.getChildAt(i) ?: continue
            val group = row as ViewGroup
            for (j in 0..group.childCount) {
                val child = group.getChildAt(j)
                if (child is ImageButton) {
                    Log.d("MAIN", "child getting reset:$i")
                    child.setImageResource(0)
                }
            }
        }
        btnNext.setImageResource(R.drawable.circle)
    }

    override fun setButtonImage(x: Int, y: Int, player: Model.Player) {
        val resId = resources.getIdentifier("btn" + (x+1) + "" + (y+1), "id", packageName)
        val view  =  find<View>(resId)
        if (player == Model.Player.CROSS) {
            (view as ImageButton).setImageResource(R.drawable.cross)
            btnNext.setImageResource(R.drawable.circle)
        } else {
            (view as ImageButton).setImageResource(R.drawable.circle)
            btnNext.setImageResource(R.drawable.cross)
        }
    }

    override fun showResult(result: Model.Player) {
        var message = ""
        if (result == Model.Player.NONE) {
            message = "Game is a DRAW"
        } else {
            message = "Game winner: " + result.toString().toUpperCase()
        }
        alert("", message) {
            yesButton {}
        }.show()
        btnNext.setImageResource(R.drawable.restart)
    }
}
