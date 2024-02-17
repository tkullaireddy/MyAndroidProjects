package com.tadiaptri.multipleactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btnGetInfo)

        btn.setOnClickListener {
            val actInt: Intent = Intent(this, Information::class.java)
            val name = findViewById<TextInputEditText>(R.id.txtPersonName).text.toString()
            val age = findViewById<TextInputEditText>(R.id.txtPersonAge).text.toString()
            actInt.putExtra("NAME", name)
            actInt.putExtra("AGE", age)

            startActivity(actInt)
        }


    }



}