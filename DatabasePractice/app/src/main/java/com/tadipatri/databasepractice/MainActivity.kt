package com.tadipatri.databasepractice

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val btn_load = findViewById<Button>(R.id.btnLoadData)
        val btn_insert = findViewById<Button>(R.id.btnInsertdata)
        val btn_cust = findViewById<Button>(R.id.btnDeletegiven)


    }
}