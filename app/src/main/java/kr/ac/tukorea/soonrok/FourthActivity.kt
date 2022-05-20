package kr.ac.tukorea.soonrok

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class FourthActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_fourth)

        var animaTitle = findViewById<TextView>(R.id.animaTitle)
        var animaContent = findViewById<TextView>(R.id.animaContent)
        var ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        var btnDelete = findViewById<Button>(R.id.btnDelete)
        var btnReturn = findViewById<Button>(R.id.btnReturn)

        var intent = intent
        var any_title = intent!!.getStringExtra("title")
        var any_content= intent!!.getStringExtra("content")
        var any_score = intent!!.getStringExtra("score")
        animaTitle.setText(any_title.toString())
        animaContent.setText(any_content.toString())
        ratingBar.setRating(any_score!!.toFloat())

        btnReturn.setOnClickListener {
            val intent = Intent(applicationContext, SecondActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnDelete.setOnClickListener {
            var dlg = AlertDialog.Builder(this@FourthActivity)
            dlg.setTitle("나의 순간")
            dlg.setMessage(any_title.toString() +" 이(가) 삭제됩니다.")
            dlg.setIcon(R.mipmap.ic_launcher_round)
            dlg.setPositiveButton("확인"){ dialog, which ->
                var path = "/data/data/kr.ac.tukorea.soonrok/files"
                val fileTitle = any_title.toString()+","+any_score.toFloat()+","+any_content.toString()
                val file = File(path+"/", fileTitle)
                file.delete()
                Toast.makeText(applicationContext, "삭제 되었습니다.", Toast.LENGTH_SHORT).show()

                var intent = Intent(applicationContext, SecondActivity::class.java)
                startActivity(intent)
                finish()
            }
            dlg.setNegativeButton("취소",null)
            dlg.show()
        }

    }
}