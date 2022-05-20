package kr.ac.tukorea.soonrok

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class SecondActivity: AppCompatActivity() {

    var anys = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_second)

        var any_list = findViewById<ListView>(R.id.listView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, anys)
        any_list.setAdapter(adapter)
        adapter.notifyDataSetChanged()

        var path = "/data/data/kr.ac.tukorea.soonrok/files"
        var directory = File(path)
        var files = directory.listFiles()

        if (!directory.exists()) {
            directory.mkdir()
            files = directory.listFiles()
        }

        var title = arrayOfNulls<String>(files.size)
        var score = arrayOfNulls<String>(files.size)
        var review = arrayOfNulls<String>(files.size)

        for (i in files.indices) {

            val arr = files[i].getName().split(",")

            val ran1 = IntRange(0, arr[0].length-1)
            val ran2 = IntRange(0, arr[1].length-1)
            val ran3 = IntRange(0, arr[2].length-1)
            title[i] = arr[0].slice(ran1)
            score[i] = arr[1].slice(ran2)
            review[i] = arr[2].slice(ran3)
            anys.add(title[i].toString())
            adapter.notifyDataSetChanged()
        }

        val writeBtn = findViewById<Button>(R.id.btnWrite)
        writeBtn.setOnClickListener {
            var intent = Intent(applicationContext, ThirdActivity::class.java)
            startActivityForResult(intent, 0)
        }

        any_list.setOnItemClickListener(AdapterView.OnItemClickListener { parent, v, position, id ->
            val intent = Intent(applicationContext, FourthActivity::class.java)
            intent.putExtra("title", title[position])
            intent.putExtra("content", review[position])
            intent.putExtra("score", score[position])
            startActivity(intent)
            finish()
        })
    }

    override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){


            var any_list = findViewById<ListView>(R.id.listView)
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, anys)
            any_list.setAdapter(adapter)

            var any_title = data!!.getStringExtra("title")

            anys.add(any_title.toString())
            adapter.notifyDataSetChanged()

            var intent = Intent(applicationContext, SecondActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}