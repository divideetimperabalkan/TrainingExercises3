package ro.iss.trainingexercises

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quiz_button.setOnClickListener {
                val intent = Intent(this, QuizActivity::class.java)
                startActivity(intent)
            overridePendingTransition(R.anim.fadeout, R.anim.fadein)
        }
    }
}
