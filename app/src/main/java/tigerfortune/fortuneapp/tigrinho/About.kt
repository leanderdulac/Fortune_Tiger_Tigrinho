package tigerfortune.fortuneapp.tigrinho

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class About : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        val back = findViewById<ImageView>(R.id.back_ab)
        val menu = findViewById<ImageView>(R.id.menu_ab)
        val setting = findViewById<ImageView>(R.id.setting_ab)
        setting.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("key", "about")
            startActivity(intent)
        }
        back.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        menu.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

    }
}