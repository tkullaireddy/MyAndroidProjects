package com.example.suduko


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow

import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CreateSudokoTable()

        val b: Button = findViewById(R.id.button)

        b.setOnClickListener { v ->
            // From here I want to move to the next screen (WishesAcitivity)
            // Step  1: Create an Intent Object
            val i : Intent = Intent(this,actCamera::class.java)
            // Step 2: get the data from edit text box
//            val name = findViewById<EditText>(R.id.person_name).text.toString()
//            // Step 3: add this name as an extra value for the intent object
//            i.putExtra("NAME",name)
            startActivity(i)
        }

    }

    private fun CreateSudokoTable() {
        val tableLayout = findViewById<TableLayout>(R.id.mainTable)



        for (i in 1..9) {

            val tableRow = TableRow(this)
            tableRow.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            for (j in 1..9) {
                val textView = TextInputEditText(this)
                textView.hint = "$i,$j"
                textView.textSize = 20f
                textView.setText(j.toString())
                textView.inputType = EditorInfo.TYPE_CLASS_NUMBER
                textView.filters = arrayOf(InputFilterMinMax(0, 9))

                textView.setBackgroundResource(R.drawable.border)
//
//                val layoutParams = LinearLayout.LayoutParams(
//                    100, // Replace with your desired width in pixels
//                    100 // Maintain existing height
//
//                )
//                textView.layoutParams = layoutParams
////                val backgroundLayer = ColorDrawable(Color.WHITE) // Optional solid background
//                val borderLayer = ShapeDrawable().apply {
//                    shape = RectShape() // Add stroke properties here
//
//                }
//
//                val layers = arrayOf(borderLayer)
//
//                val layerDrawable = LayerDrawable(layers)

//                textView.background = layerDrawable

                tableRow.addView(textView)
            }
            tableLayout.addView(tableRow)
        }
    }



}


