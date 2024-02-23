package tigerfortune.fortuneapp.tigrinho

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tigerfortune.fortuneapp.tigrinho.databinding.ActivityStartGameBinding

class StartGame : AppCompatActivity() {
    lateinit var stg: ActivityStartGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stg = ActivityStartGameBinding.inflate(layoutInflater)
        setContentView(stg.root)
        stg.backSg.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        stg.menuSg.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        stg.settingSg.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            intent.putExtra("key", "stg")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        stg.level1.setOnClickListener {
            startActivity(Intent(this, StartLevel1::class.java))
        }
        stg.level2.setOnClickListener {
            startActivity(Intent(this, StartLevel2::class.java))
        }
        stg.level3.setOnClickListener {
            startActivity(Intent(this, StartLevel3::class.java))
        }
    }
}