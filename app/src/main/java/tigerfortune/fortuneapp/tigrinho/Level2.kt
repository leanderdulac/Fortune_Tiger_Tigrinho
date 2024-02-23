package tigerfortune.fortuneapp.tigrinho

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class Level2 : AppCompatActivity() {
    private lateinit var board: Array<Array<String>>
    private var currentPlayer = "X"
    private var gameOver = false
    private var score = 0
    private var score_o = 0
    private lateinit var textView: TextView
    private lateinit var imageViews: Array<Array<ImageView>>
    private var playerCanMove = true

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level2)
        val backl2 = findViewById<ImageView>(R.id.level_l2)
        val menul2 = findViewById<ImageView>(R.id.menu_l2)
        backl2.setOnClickListener {
            startActivity(Intent(this, StartGame::class.java))
        }
        menul2.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        textView = findViewById(R.id.textView)
        imageViews = Array(3) { row ->
            Array(3) { col ->
                findViewById(resources.getIdentifier("imageView${row * 3 + col + 1}",
                    "id",
                    packageName))
            }
        }

        initializeBoard()
    }

    private fun initializeBoard() {
        board = Array(3) { Array(3) { "" } }
        currentPlayer = "X"
        gameOver = false

        for (row in imageViews) {
            for (imageView in row) {
                imageView.apply {
                    setImageResource(0)
                    isEnabled = playerCanMove
                }
            }
        }
    }

    fun onCellClick(view: View) {
        if (!gameOver && playerCanMove) {
            val imageView = view as ImageView
            val row = imageView.tag.toString().substring(0, 1).toInt()
            val col = imageView.tag.toString().substring(1, 2).toInt()

            if (board[row][col].isEmpty()) {
                board[row][col] = currentPlayer

                imageView.isEnabled = false

                imageView.setImageResource(if (currentPlayer == "X") R.drawable.x else R.drawable.ring2)

                if (checkWinCondition(row, col)) {
                    gameOver = true
                    if(currentPlayer == "X"){
                        score++
                        Toast.makeText(this, "Congrats, you won!", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        score_o++
                    }
                    textView.text = "$score:$score_o"
                    Handler().postDelayed({
                        initializeBoard()
                    },1500)

                } else if (isBoardFull()) {
                    gameOver = true
                    textView.text = "$score:$score_o"
                    Handler().postDelayed({
                        initializeBoard()
                    },1500)
                } else {
                    currentPlayer = if (currentPlayer == "X") "O" else "X"
                    textView.text = "$score:$score_o"
                    playerCanMove = false
                    robotMove()
                }
            }
        }
    }

    private fun checkWinCondition(row: Int, col: Int): Boolean {
        if (board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0].isNotEmpty())
            return true

        if (board[0][col] == board[1][col] && board[1][col] == board[2][col] && board[0][col].isNotEmpty())
            return true

        if (row == col && board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0].isNotEmpty())
            return true

        if (row + col == 2 && board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2].isNotEmpty())
            return true

        return false
    }

    private fun isBoardFull(): Boolean {
        for (row in board) {
            for (cell in row) {
                if (cell.isEmpty()) {
                    return false
                }
            }
        }
        return true
    }

    private fun robotMove() {
        Handler().postDelayed({
            var moved = false
            var robotRow = -1
            var robotCol = -1

            for (i in 0 until 3) {
                for (j in 0 until 3) {
                    if (board[i][j].isEmpty()) {
                        robotRow = i
                        robotCol = j
                        moved = true
                        break
                    }
                }
                if (moved) {
                    break
                }
            }

            if (moved) {
                board[robotRow][robotCol] = currentPlayer
                imageViews[robotRow][robotCol].setImageResource(
                    if (currentPlayer == "X") R.drawable.x else R.drawable.ring2
                )
                imageViews[robotRow][robotCol].isEnabled = false
                if (checkWinCondition(robotRow, robotCol)) {
                    gameOver = true
                    if(currentPlayer == "X"){
                        score++
                        Toast.makeText(this, "Congrats, you won!", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        score_o++
                    }
                    textView.text = "$score:$score_o"
                    Handler().postDelayed({
                        initializeBoard()
                    },1500)
                    playerCanMove = true
                } else if (isBoardFull()) {
                    gameOver = true
                    textView.text = "$score:$score_o"
                    Handler().postDelayed({
                        initializeBoard()
                    },1500)
                } else {
                    currentPlayer = if (currentPlayer == "X") "O" else "X"
                    textView.text = "$score:$score_o"
                    playerCanMove = true
                }
            }
        }, 500)
    }
}