package com.example.suduko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        settextfilters()
    }
    fun settextfilters() {

        val ids = setOf<Int>(R.id.a11,R.id.a12,R.id.a13,R.id.a14,R.id.a15,R.id.a16,R.id.a17,R.id.a18,R.id.a19,
                R.id.a21,R.id.a22,R.id.a23,R.id.a24,R.id.a25,R.id.a26,R.id.a27,R.id.a28,R.id.a29)

        for (i  in ids) {
            val editText = findViewById<TextInputEditText>(i)
            editText.setFilters(arrayOf(InputFilterMinMax(0, 9)))
        }
    }
}


