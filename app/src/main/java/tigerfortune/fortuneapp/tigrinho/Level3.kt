package tigerfortune.fortuneapp.tigrinho


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import java.util.*

class Level3 : AppCompatActivity() {
    private lateinit var frameLayout: FrameLayout
    private lateinit var block: ImageView
    private lateinit var scoreTextView: TextView
    private lateinit var leftButton: ImageView
    private lateinit var rightButton: ImageView

    private val random = Random()
    private var score_1 = 0
    private var score_2 = 0

    private val imageIds = arrayOf(
        R.drawable.coin1,
        R.drawable.coin2,
        R.drawable.bomb
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level3)

        val backl3 = findViewById<ImageView>(R.id.level_l3)
        val menul3 = findViewById<ImageView>(R.id.menu_l3)
        backl3.setOnClickListener {
            startActivity(Intent(this, StartGame::class.java))
        }
        menul3.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        frameLayout = findViewById(R.id.frameLayout)
        block = findViewById(R.id.block)
        scoreTextView = findViewById(R.id.scoreTextView)
        leftButton = findViewById(R.id.leftButton)
        rightButton = findViewById(R.id.rightButton)

        leftButton.setOnTouchListener { _, event ->
            handleLeftButtonTouch(event)
            true
        }

        rightButton.setOnTouchListener { _, event ->
            handleRightButtonTouch(event)
            true
        }

        block.setOnTouchListener { _, event ->
            handleTouch(event)
            true
        }
        ViewCompat.setTranslationZ(block, 1f)
        spawnImages()
    }

    private fun handleLeftButtonTouch(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> moveBlockLeft()
            MotionEvent.ACTION_UP -> stopBlock()
        }
    }

    private fun handleRightButtonTouch(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> moveBlockRight()
            MotionEvent.ACTION_UP -> stopBlock()
        }
    }

    private fun moveBlockLeft() {
        val newX = block.x - 50 // Adjust the value based on your preference
        if (newX >= 0) {
            block.x = newX
        }
    }

    private fun moveBlockRight() {
        val newX = block.x + 50 // Adjust the value based on your preference
        if (newX <= frameLayout.width - block.width) {
            block.x = newX
        }
    }

    private fun stopBlock() {
        // You can add any logic for stopping the block if needed
    }

    private fun handleTouch(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_MOVE -> {
                val newX = event.rawX - block.width / 2
                if (newX >= 0 && newX <= frameLayout.width - block.width) {
                    block.x = newX
                }
            }
        }
    }

    // ...

    private fun spawnImages() {
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                val image = ImageView(this@Level3)
                val imageId = imageIds[random.nextInt(imageIds.size)]
                image.setImageResource(imageId)

                val imageSize = 100 // Adjust the size based on your preference
                val x = if (frameLayout.width > imageSize) {
                    random.nextInt(frameLayout.width - imageSize)
                } else {
                    0
                }

                val y = 0

                val layoutParams = FrameLayout.LayoutParams(imageSize, imageSize)
                image.layoutParams = layoutParams
                image.x = x.toFloat()
                image.y = y.toFloat()

                frameLayout.addView(image)

                image.animate().translationY(frameLayout.height.toFloat()).setDuration(3000)
                    .withEndAction {
                        frameLayout.removeView(image)
                        handleCollision(image, imageId)
                    }

                handleCollision(image, imageId)
                Handler().postDelayed({
                    handleCollision(image, imageId)
                },1000)

                handler.postDelayed(this, 2000)
            }
        })

    }

    private fun handleCollision(image: ImageView, imageId: Int) {
        val blockX = block.x
        val blockY = block.y
        val blockWidth = block.width
        val blockHeight = block.height

        val imageX = image.x
        val imageY = image.y
        val imageWidth = image.width
        val imageHeight = image.height

        // Выводим координаты для отладки
        println("Block Rect: RectF($blockX, $blockY, ${blockX + blockWidth}, ${blockY + blockHeight})")
        println("Image Rect: RectF($imageX, $imageY, ${imageX + imageWidth}, ${imageY + imageHeight})")

        if (blockX <= imageX + imageWidth &&
            blockX + blockWidth >= imageX &&
            blockY <= imageY + imageHeight &&
            blockY + blockHeight >= imageY
        ) {
            frameLayout.removeView(image)

            when (imageId) {
                R.drawable.coin1 -> score_1 += 1
                R.drawable.coin2 -> score_2 += 2
                R.drawable.bomb->{
                    score_1 = 0
                    score_2=0
                }
            }

            scoreTextView.text = "$score_1:$score_2"
        }
    }



}
