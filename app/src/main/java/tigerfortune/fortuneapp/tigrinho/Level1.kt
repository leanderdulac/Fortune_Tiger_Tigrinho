package tigerfortune.fortuneapp.tigrinho

import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Level1 : AppCompatActivity() {

    private val rows = 6
    private var counter = 0
    private val columns = 4
    private val cardCount = rows * columns / 2
    private val images = mutableListOf<Int>()
    private lateinit var gridLayout: ViewGroup
    private lateinit var resetButton: ImageView

    private var draggedImageView: ImageView? = null
    private var draggedImageTag: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level1)

        gridLayout = findViewById(R.id.gridLayout)
        resetButton = findViewById(R.id.reset)
        val backl1 = findViewById<ImageView>(R.id.level_l1)
        val menul1 = findViewById<ImageView>(R.id.menu_l1)
        backl1.setOnClickListener {
            startActivity(Intent(this, StartGame::class.java))
        }
        menul1.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        for (i in 0 until cardCount) {
            images.add(i % (cardCount / 2) + 1)
        }

        for (i in 0 until rows) {
            for (j in 0 until columns) {
                val imageView: ImageView = createCard(i * columns + j)
                gridLayout.addView(imageView)
            }
        }

        resetButton.setOnClickListener {
            resetGame()
        }
    }

    private fun createCard(index: Int): ImageView {
        val imageView = ImageView(this)
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        imageView.setPadding(8, 8, 8, 8)

        val resourceName = "image${images[index % cardCount]}"
        val resourceId = resources.getIdentifier(resourceName, "drawable", packageName)

        imageView.setImageResource(resourceId)
        imageView.tag = images[index % cardCount]

        imageView.setOnTouchListener { _, event ->
            onTouch(imageView, event)
        }

        imageView.setOnDragListener { _, dragEvent ->
            onDrag(imageView, dragEvent)
        }

        return imageView
    }

    private fun onTouch(view: ImageView, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val clipData = ClipData.newPlainText("", "")
                val shadowBuilder = View.DragShadowBuilder(view)
                view.startDrag(clipData, shadowBuilder, view, 0)
                draggedImageView = view
                draggedImageTag = view.tag as Int
                return true
            }
            else -> return false
        }
    }

    private fun onDrag(view: View, dragEvent: DragEvent): Boolean {
        when (dragEvent.action) {
            DragEvent.ACTION_DROP -> {
                val droppedImageView = dragEvent.localState as ImageView
                val droppedImageTag = droppedImageView.tag as Int

                val targetImageView = view as ImageView
                val targetImageTag = targetImageView.tag as Int

                if (draggedImageTag == targetImageTag && droppedImageView != targetImageView) {
                    removeCards(droppedImageView, targetImageView)
                } else {
                    droppedImageView.visibility = View.VISIBLE
                }

                draggedImageView = null
                draggedImageTag = null
                return true
            }
            else -> return true
        }
    }


    private fun removeCards(card1: View?, card2: View?) {
        Toast.makeText(this, "Cards match!", Toast.LENGTH_SHORT).show()

        if (card1?.tag == card2?.tag) {
            gridLayout.removeView(card1)
            gridLayout.removeView(card2)
            counter++
            checkGameEnd()
        } else {
            card1?.visibility = View.VISIBLE
            card2?.visibility = View.VISIBLE
        }
    }


    private fun checkGameEnd() {
        if (counter==12){
            resetGame()
        }
    }

    private fun resetGame() {
        gridLayout.removeAllViews()
        draggedImageView = null
        draggedImageTag = null
        images.shuffle()

        for (i in 0 until rows) {
            for (j in 0 until columns) {
                val imageView: ImageView = createCard(i * columns + j)
                gridLayout.addView(imageView)
            }
        }
    }
}
