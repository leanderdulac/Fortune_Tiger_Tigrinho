package tigerfortune.fortuneapp.tigrinho

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class StartLevel1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_level1)
        val back = findViewById<ImageView>(R.id.back_st1)
        val next = findViewById<ImageView>(R.id.next_st1)
        val menu = findViewById<ImageView>(R.id.menu_st1)
        val level = findViewById<ImageView>(R.id.level_st1)
        level.setOnClickListener {
            startActivity(Intent(this, StartGame::class.java))
        }
        back.setOnClickListener {
            startActivity(Intent(this, StartGame::class.java))
        }
        next.setOnClickListener {
            startActivity(Intent(this, Level1::class.java))
        }
        menu.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}