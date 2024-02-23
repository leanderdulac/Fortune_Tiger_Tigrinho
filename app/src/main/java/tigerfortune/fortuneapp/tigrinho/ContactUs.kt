package tigerfortune.fortuneapp.tigrinho

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class ContactUs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)
        val back = findViewById<ImageView>(R.id.back_cu)
        val menu = findViewById<ImageView>(R.id.menu_cu)
        val setting = findViewById<ImageView>(R.id.setting_cu)
        setting.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            intent.putExtra("key", "cu")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
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