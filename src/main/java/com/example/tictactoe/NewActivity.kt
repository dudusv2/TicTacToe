package com.example.tictactoe

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


@Suppress(
    "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS"
)
class NewActivity : AppCompatActivity() {

    private var buttons = arrayOfNulls<Button>(100)
    private var turn = true
    private var roundCount = 0
    private var p1Score = 0
    private var p2Score = 0
    private lateinit var tvp1: TextView
    private lateinit var tvp2: TextView
    // private var computer: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        tvp1 = findViewById(R.id.text_view_p1)
        tvp2 = findViewById(R.id.text_view_p2)

        // computer = this.intent.extras!!.getBoolean("computer")

        // if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        //    window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        // } else {
        //     window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        //  }
        supportActionBar!!.hide()
        turn = savedInstanceState?.getBoolean("turn") ?: true
        switchTurn()
        for (x in 0..99) {
            val btn = "button_$x"
            val resID = resources.getIdentifier(btn, "id", packageName)
            buttons[x] = findViewById(resID)
            buttons[x]!!.setOnClickListener {
                if ((buttons[x] as Button).text.toString() != "") return@setOnClickListener
                if (turn) {
                    buttons[x]!!.text = "X"
                    buttons[x]!!.isActivated = true
                } else {
                    buttons[x]!!.text = "O"
                    buttons[x]!!.isEnabled = false
                }
                roundCount++

                if (checkForWin()) {
                    if (turn) {
                        player1Wins()
                    } else {
                        player2Wins()
                    }
                } else if (roundCount == 99) {
                    draw()
                } else {
                    switchTurn()
                    // if (computer && !turn) cpu()
                }
            }
        }
        val buttonReset = findViewById<Button>(R.id.button_reset)
        buttonReset.setOnClickListener {
            resetGame()
        }
    }


    private fun checkForWin(): Boolean {
        // Log.i("win ? ", (checkHorizontal() || checkVertical()).toString())
        return (checkHorizontal() || checkVertical() || checkLeftDiagonally() || checkRightDiagonally())
    }

    private fun checkVertical(): Boolean {
        for (x in 4..94 step 10) {
            // Czy dwa środkowe mają takie same niepuste znaki
            if (buttons[x]!!.text.toString() != "" && buttons[x]!!.text.toString() == buttons[x + 1]!!.text.toString() &&
                ((buttons[x - 3]!!.text.toString() == buttons[x]!!.text.toString() &&
                        buttons[x - 2]!!.text.toString() == buttons[x]!!.text.toString() &&
                        buttons[x - 1]!!.text.toString() == buttons[x]!!.text.toString() &&
                        buttons[x + 1]!!.text.toString() == buttons[x]!!.text.toString()) ||
                        (buttons[x - 2]!!.text.toString() == buttons[x]!!.text.toString() &&
                                buttons[x - 1]!!.text.toString() == buttons[x]!!.text.toString() &&
                                buttons[x + 1]!!.text.toString() == buttons[x]!!.text.toString() &&
                                buttons[x + 2]!!.text.toString() == buttons[x]!!.text.toString()) ||
                        (buttons[x - 1]!!.text.toString() == buttons[x]!!.text.toString() &&
                                buttons[x + 1]!!.text.toString() == buttons[x]!!.text.toString() &&
                                buttons[x + 2]!!.text.toString() == buttons[x]!!.text.toString() &&
                                buttons[x + 3]!!.text.toString() == buttons[x]!!.text.toString()) ||
                        (buttons[x + 1]!!.text.toString() == buttons[x]!!.text.toString() &&
                                buttons[x + 2]!!.text.toString() == buttons[x]!!.text.toString() &&
                                buttons[x + 3]!!.text.toString() == buttons[x]!!.text.toString() &&
                                buttons[x + 4]!!.text.toString() == buttons[x]!!.text.toString()))
            ) return true
            // Jeśli lewy środek jest zaznaczony i jest różny od prawgo środka
            if (buttons[x]!!.text.toString() != "" &&
                buttons[x - 4]!!.text.toString() == buttons[x]!!.text.toString() &&
                buttons[x - 3]!!.text.toString() == buttons[x]!!.text.toString() &&
                buttons[x - 2]!!.text.toString() == buttons[x]!!.text.toString() &&
                buttons[x - 1]!!.text.toString() == buttons[x]!!.text.toString()
            ) return true
            // Jeśli prawy środek jest zaznaczony i jest różny od lewego środka
            if (buttons[x + 1]!!.text.toString() != "" &&
                buttons[x + 5]!!.text.toString() == buttons[x + 1]!!.text.toString() &&
                buttons[x + 4]!!.text.toString() == buttons[x + 1]!!.text.toString() &&
                buttons[x + 3]!!.text.toString() == buttons[x + 1]!!.text.toString() &&
                buttons[x + 2]!!.text.toString() == buttons[x + 1]!!.text.toString()
            )
                return true
        }
        return false
    }

    private fun checkHorizontal(): Boolean {
        for (x in 40..49) {
            // Czy dwa środkowe mają takie same niepuste znaki
            if (buttons[x]!!.text.toString() != "" && buttons[x]!!.text.toString() == buttons[x + 10]!!.text.toString() &&
                (
                        (buttons[x - 30]!!.text.toString() == buttons[x]!!.text.toString() &&
                                buttons[x - 20]!!.text.toString() == buttons[x]!!.text.toString() &&
                                buttons[x - 10]!!.text.toString() == buttons[x]!!.text.toString() &&
                                buttons[x + 10]!!.text.toString() == buttons[x]!!.text.toString()) ||
                                (buttons[x - 20]!!.text.toString() == buttons[x]!!.text.toString() &&
                                        buttons[x - 10]!!.text.toString() == buttons[x]!!.text.toString() &&
                                        buttons[x + 10]!!.text.toString() == buttons[x]!!.text.toString() &&
                                        buttons[x + 20]!!.text.toString() == buttons[x]!!.text.toString()) ||
                                (buttons[x - 10]!!.text.toString() == buttons[x]!!.text.toString() &&
                                        buttons[x + 10]!!.text.toString() == buttons[x]!!.text.toString() &&
                                        buttons[x + 20]!!.text.toString() == buttons[x]!!.text.toString() &&
                                        buttons[x + 30]!!.text.toString() == buttons[x]!!.text.toString()) ||
                                (buttons[x + 10]!!.text.toString() == buttons[x]!!.text.toString() &&
                                        buttons[x + 20]!!.text.toString() == buttons[x]!!.text.toString() &&
                                        buttons[x + 30]!!.text.toString() == buttons[x]!!.text.toString() &&
                                        buttons[x + 40]!!.text.toString() == buttons[x]!!.text.toString())
                        )
            ) return true

            // Jeśli górny środek jest zaznaczony i jest różny od dolnego środka
            if (buttons[x]!!.text.toString() != "" &&
                buttons[x - 40]!!.text.toString() == buttons[x]!!.text.toString() &&
                buttons[x - 30]!!.text.toString() == buttons[x]!!.text.toString() &&
                buttons[x - 20]!!.text.toString() == buttons[x]!!.text.toString() &&
                buttons[x - 10]!!.text.toString() == buttons[x]!!.text.toString()
            ) return true

            // Jeśli dolny środek jest zaznaczony i jest różny od górnego środka
            if (buttons[x + 10]!!.text.toString() != "" &&
                buttons[x + 50]!!.text.toString() == buttons[x + 10]!!.text.toString() &&
                buttons[x + 40]!!.text.toString() == buttons[x + 10]!!.text.toString() &&
                buttons[x + 30]!!.text.toString() == buttons[x + 10]!!.text.toString() &&
                buttons[x + 20]!!.text.toString() == buttons[x + 10]!!.text.toString()
            ) return true
        }
        return false
    }

    private fun checkLeftDiagonally(): Boolean {
        for (x in 4..9) for (y in 0..50 step 10) if (buttons[y + x]!!.text.toString() != "" &&
            buttons[y + x + 9]!!.text.toString() == buttons[x + y]!!.text.toString() &&
            buttons[y + x + 18]!!.text.toString() == buttons[x + y]!!.text.toString() &&
            buttons[y + x + 27]!!.text.toString() == buttons[x + y]!!.text.toString() &&
            buttons[y + x + 36]!!.text.toString() == buttons[x + y]!!.text.toString()
        )
            return true
        return false
    }

    private fun checkRightDiagonally(): Boolean {
        for (x in 0..5) for (y in 0..50 step 10) if (buttons[y + x]!!.text.toString() != "" &&
            buttons[y + x + 11]!!.text.toString() == buttons[x + y]!!.text.toString() &&
            buttons[y + x + 22]!!.text.toString() == buttons[x + y]!!.text.toString() &&
            buttons[y + x + 33]!!.text.toString() == buttons[x + y]!!.text.toString() &&
            buttons[y + x + 44]!!.text.toString() == buttons[x + y]!!.text.toString()
        )
            return true
        return false
    }


    @SuppressLint("SetTextI18n")
    private fun updatePointsText() {
        tvp1.text = "Player 1: $p1Score"
        tvp2.text = "Player 2 : $p2Score"
    }

    private fun resetGame() {
        p1Score = 0
        p2Score = 0
        updatePointsText()
        resetBoard()
    }

    private fun resetBoard() {
        for (i in 0..99) {
            buttons[i]!!.text = ""
            buttons[i]!!.isActivated = false
            buttons[i]!!.isEnabled = true
        }
        roundCount = 0
        switchTurn()
    }

    private fun switchTurn() {
        turn = !turn
        if (turn) {
            tvp1.isEnabled = true
            tvp2.isEnabled = false
        } else {
            tvp1.isEnabled = false
            tvp2.isEnabled = true
        }

    }

    private fun player1Wins() {
        p1Score++
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show()
        updatePointsText()
        resetBoard()
    }

    private fun player2Wins() {
        p2Score++
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show()
        updatePointsText()
        resetBoard()
    }

    private fun draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show()
        resetBoard()
    }

//    private fun cpu() {
//        var arr = arrayOf(-6, -5, -4, -1, 1, 4, 5, 6)
//        if (roundCount == 1) {
//            var rand = (0..24).random()
//            while (buttons[rand]!!.text != "") {
//                rand = (0..24).random()
//            }
//            buttons[rand]!!.performClick()
//        } else {
//            for (i in 0..24) {
//                if (buttons[i]!!.text == "O") {
//                    if (i == 9 || i == 14 || i == 19) arr = arrayOf(-6, -5, -1, 4, 5)
//                    else if (i == 5 || i == 10 || i == 15) arr = arrayOf(6, -5, 1, -4, 5)
//                    else if (i == 2 || i == 3 || i == 1) arr = arrayOf(1, -1, 4, 5, 6)
//                    else if (i == 21 || i == 22 || i == 23) arr = arrayOf(1, -1, -4, -5, -6)
//                    else if (i == 0) arr = arrayOf(1, 5, 6)
//                    else if (i == 24) arr = arrayOf(-1, -5, -6)
//                    else if (i == 4) arr = arrayOf(-1, 5, 4)
//                    else if (i == 20) arr = arrayOf(1, -5, -4)
//                    for (j in arr) {
//                        val x = i + arr.random()
//                        if (x in 0..24) {
//                            if (buttons[x]!!.text == "") {
//                                buttons[x]!!.performClick()
//                                return
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState!!.putBoolean("turn", turn)
    }
}
