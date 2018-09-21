package com.example.rich.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton

class MainActivity : AppCompatActivity() {

    private val model = Model()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onNext(view: View) {
        if (model.gameResult() != null) {
            model.reset()
            for (i in 0..tableLayout.childCount) {
                val child = tableLayout.getChildAt(i)
                if (child is ImageButton) {
                    (child as ImageButton).setImageResource(0)
                }
            }
            btnNext.setImageResource(R.drawable.circle)
        }
    }

    fun onClick(view: View) {
        val stringId = resources.getResourceEntryName(view.id)
        val moved= model.markCurrentPlayer(stringId.substring(3)) ?: return
        Log.d("MAIN", "moved = $moved")
        if (moved == Model.Player.CROSS) {
            (view as ImageButton).setImageResource(R.drawable.cross)
            btnNext.setImageResource(R.drawable.circle)
        } else {
            (view as ImageButton).setImageResource(R.drawable.circle)
            btnNext.setImageResource(R.drawable.cross)
        }
        val result = model.gameResult()
        if(result != null) {

            if (result == Model.Player.NONE) {
                alert("", "Game is a DRAW") {
                    yesButton {}
                }.show()
            } else {
                alert("", "Game winner: " + result.toString().toUpperCase()) {
                    yesButton {  }
                }.show()
            }
            btnNext.setImageResource(R.drawable.restart)
        }
    }
}
