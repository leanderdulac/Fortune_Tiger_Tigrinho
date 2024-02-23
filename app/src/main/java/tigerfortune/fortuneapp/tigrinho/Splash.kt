package tigerfortune.fortuneapp.tigrinho

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            val next_vvod = findViewById<ImageView>(R.id.next_vvod)
            val nameEditText = findViewById<EditText>(R.id.editTextName)
            val submitButton = findViewById<ImageView>(R.id.next_vvod)
            next_vvod.visibility = View.VISIBLE
            val rootLayout = findViewById<View>(R.id.cont)
            rootLayout.setBackgroundResource(R.drawable.name_fon)
            nameEditText.visibility = View.VISIBLE
            submitButton.visibility = View.VISIBLE
            submitButton.setOnClickListener {
                val enteredName = nameEditText.text.toString()
                if (enteredName.isNotEmpty()) {
                    val intent = Intent(this, Menu::class.java)
                    intent.putExtra("NAME", enteredName)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    val intent2 = Intent(this, Settings::class.java)
                    intent2.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    intent2.putExtra("NAME", enteredName)
                    startActivity(intent2)
                    finish()
                } else {
                    Toast.makeText(this, "No name", Toast.LENGTH_SHORT).show()
                }
            }
        }, 3000)
    }
}