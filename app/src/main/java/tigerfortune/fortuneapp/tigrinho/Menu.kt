package tigerfortune.fortuneapp.tigrinho

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import tigerfortune.fortuneapp.tigrinho.databinding.ActivityMenuBinding

class Menu : AppCompatActivity() {
    lateinit var bind: ActivityMenuBinding
    private lateinit var nameTextView: TextView
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(bind.root)
        nameTextView = findViewById(R.id.naming)
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        var enteredName = intent.getStringExtra("NAME")
        bind.naming.setText("Hello, $enteredName")
        bind.start.setOnClickListener {
            startActivity(Intent(this, StartGame::class.java))
        }
        bind.about.setOnClickListener {
            startActivity(Intent(this, About::class.java))
        }
        bind.contact.setOnClickListener {
            startActivity(Intent(this, ContactUs::class.java))
        }
        bind.privacy.setOnClickListener {
            startActivity(Intent(this, Privacy::class.java))
        }
        bind.level.setOnClickListener {
            startActivity(Intent(this, StartLevel1::class.java))
        }
        bind.setting.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("key", "menu")
            startActivity(intent)
        }
    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }
}