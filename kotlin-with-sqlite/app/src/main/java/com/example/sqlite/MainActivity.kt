package com.example.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    val list: ArrayList<String> = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            connetionSqlite()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun openFragment(view: View) {
        println("list: $list")
        val fragment = FirstFragment()
        val bundle = Bundle()
        bundle.putString("name", "John")
        fragment.arguments = bundle
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun connetionSqlite() {
        val db = openOrCreateDatabase("sqlite.db", MODE_PRIVATE, null)
        val sqlCreateTable = "CREATE TABLE IF NOT EXISTS user " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "phone INTEGER, " +
                "email TEXT)"

        db.execSQL(sqlCreateTable)

//        val sqlInsert = "INSERT INTO user (name, phone, email) VALUES ('John', " +
//                "'6262632','asdfja@gmail.com')"
//
//        db.execSQL(sqlInsert)
        val cursor = db.rawQuery(/* sql = */ "SELECT * FROM user", /* selectionArgs = */ null)
        val nameIndex = cursor.getColumnIndex("name")
        val phoneIndex = cursor.getColumnIndex("phone")
        val emailIndex = cursor.getColumnIndex("email")

        while (cursor.moveToNext()) {
            val maps:HashMap<String,String> = HashMap<String,String>()
            maps.put("name",cursor.getString(nameIndex))
            maps.put("phone",cursor.getString(phoneIndex))
            maps.put("email",cursor.getString(emailIndex))
            list.add(maps.toString())
        }
        cursor.close()
    }
}