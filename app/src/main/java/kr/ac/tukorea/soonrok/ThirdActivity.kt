package kr.ac.tukorea.soonrok

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ThirdActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_third)

        var OkBtn = findViewById<Button>(R.id.btnOk)
        var ReturnBtn = findViewById<Button>(R.id.btnReturn)
        val edit_title = findViewById<EditText>(R.id.animaTitle)
        val edit_content = findViewById<EditText>(R.id.animaContent)
        var ratingBar = findViewById<RatingBar>(R.id.ratingBar)

        OkBtn.setOnClickListener {

            if (edit_title.length() == 0) {
                Toast.makeText(applicationContext, "제목을 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else if (edit_content.length() == 0) {
                Toast.makeText(applicationContext, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                var editTitle = edit_title.text.toString()
                var editContent = edit_content.text.toString()
                var ratingBarr = ratingBar.getRating().toDouble()
                var outFs = openFileOutput(
                    edit_title.text.toString() + "," + ratingBarr.toString() + "," + edit_content.text.toString(),
                    Context.MODE_PRIVATE
                )
                outFs.write(edit_title.text.toString().toByteArray())
                outFs.close()
                Toast.makeText(
                    applicationContext,
                    ("' " + edit_title.text.toString() +" ' 순간을 기록"),
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(applicationContext, SecondActivity::class.java)
                intent.putExtra("title", editTitle)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
        ReturnBtn.setOnClickListener {
            finish()
        }
    }
}