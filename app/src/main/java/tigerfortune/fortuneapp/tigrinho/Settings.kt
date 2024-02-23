package tigerfortune.fortuneapp.tigrinho

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.media.MediaPlayer
import android.view.View

class Settings : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private var  namee = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val menu = findViewById<ImageView>(R.id.menu_st)
        val off = findViewById<ImageView>(R.id.sof)
        val on = findViewById<ImageView>(R.id.son)
        val ofik = findViewById<ImageView>(R.id.offf)
        val onik = findViewById<ImageView>(R.id.onn)
        val ke = intent?.getStringExtra("key")
        val prih = intent.getStringExtra("NAME")
        if(prih != ""){
            namee = prih.toString()
        }
        handleIntentKey(ke)
        menu.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("NAME", namee)
            startActivity(intent)
        }
        on.setOnClickListener {
            startMusic()
            ofik.visibility = View.INVISIBLE
            onik.visibility = View.VISIBLE
        }
        off.setOnClickListener {
            stopMusic()
            ofik.visibility = View.VISIBLE
            onik.visibility = View.INVISIBLE
        }
    }
    private fun startMusic() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.fonnnnnnn)
            mediaPlayer?.start()
        } else if (!mediaPlayer!!.isPlaying) {
            mediaPlayer?.start()
        }
    }

    private fun stopMusic() {
        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val ke = intent?.getStringExtra("key")
        handleIntentKey(ke)
    }
    private fun handleIntentKey(key: String?) {
        val back = findViewById<ImageView>(R.id.back_st)
        if(key == "stg"){
            back.setOnClickListener {
                startActivity(Intent(this, StartGame::class.java))
            }
        }
        else if(key == "menu"){
            back.setOnClickListener {
                val intent = Intent(this, Menu::class.java)
                intent.putExtra("NAME", namee)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        }
        else if(key == "about"){
            back.setOnClickListener {
                startActivity(Intent(this, About::class.java))
            }
        }
        else if(key == "cu"){
            back.setOnClickListener {
                startActivity(Intent(this, ContactUs::class.java))
            }
        }
        else{
            back.setOnClickListener {
                val intent = Intent(this, Menu::class.java)
                intent.putExtra("NAME", namee)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        }
    }
}