package com.cis.lab.androidsqlite

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val DB_NAME = "todo.db"
        button1.setOnClickListener{
            val dbHelper = DBHelper(this,DB_NAME,null,1)
            val newTask:Task = Task(editText.text.toString())
            dbHelper.addTask(newTask)

            Toast.makeText(this,editText.text.toString()+" Add to DB",Toast.LENGTH_SHORT).show()
        }
        button2.setOnClickListener{
            val dbHelper=DBHelper(this,DB_NAME,null,1)
            val data:Cursor? = dbHelper.getAllTask()

            data!!.moveToFirst()

            textView.text=""
            textView.append(data.getString(data.getColumnIndex("taskname")))

            while (data.moveToNext()){
                textView.append("\n")
                textView.append(data.getString(data.getColumnIndex("taskname")))
            }
        }
    }
}
