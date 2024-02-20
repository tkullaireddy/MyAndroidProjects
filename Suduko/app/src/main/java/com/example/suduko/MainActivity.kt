package com.example.suduko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        settextfilters()

        val parentView = findViewById<TableLayout>(R.id.mainTable)



        for (i in 1 ..9){

            val tblrow = TableRow(this)

            val tbllayoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            tbllayoutParams.weight=1F

//            parentView.addView(tblrow)

//            for(j in 1 .. 9){
                val textView = TextInputEditText(this)
                textView.hint = "$i,$i"
        textView.setText("$i,$i")
                textView.textSize = 16f



                val layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                layoutParams.weight=1F
                textView.layoutParams = layoutParams
                parentView.addView(textView)


//            }
        }




    }
    private fun settextfilters() {

//        val ids = setOf<Int>(R.id.a11,R.id.a12,R.id.a13,R.id.a14,R.id.a15,R.id.a16,R.id.a17,R.id.a18,R.id.a19,
//                R.id.a21,R.id.a22,R.id.a23,R.id.a24,R.id.a25,R.id.a26,R.id.a27,R.id.a28,R.id.a29)
//
//        for (i  in ids) {
//            val editText = findViewById<TextInputEditText>(i)
//            editText.filters = arrayOf(InputFilterMinMax(0, 9))
//        }
    }

    private fun GetID(aRow:Int,aCol:Int):Int {
        val chkstr = "$aRow,$aCol"
        var retstr: Int
        retstr = when (chkstr) {
            "1,1"-> R.string.a11
            "1,2"-> R.string.a12
            "1,3"-> R.string.a13
            "1,4"-> R.string.a14
            "1,5"-> R.string.a15
            "1,6"-> R.string.a16
            "1,7"-> R.string.a17
            "1,8"-> R.string.a18
            "1,9"-> R.string.a19
            "2,1"-> R.string.a21
            "2,2"-> R.string.a22
            "2,3"-> R.string.a23
            "2,4"-> R.string.a24
            "2,5"-> R.string.a25
            "2,6"-> R.string.a26
            "2,7"-> R.string.a27
            "2,8"-> R.string.a28
            "2,9"-> R.string.a29
            "3,1"-> R.string.a31
            "3,2"-> R.string.a32
            "3,3"-> R.string.a33
            "3,4"-> R.string.a34
            "3,5"-> R.string.a35
            "3,6"-> R.string.a36
            "3,7"-> R.string.a37
            "3,8"-> R.string.a38
            "3,9"-> R.string.a39
            "4,1"-> R.string.a41
            "4,2"-> R.string.a42
            "4,3"-> R.string.a43
            "4,4"-> R.string.a44
            "4,5"-> R.string.a45
            "4,6"-> R.string.a46
            "4,7"-> R.string.a47
            "4,8"-> R.string.a48
            "4,9"-> R.string.a49
            "5,1"-> R.string.a51
            "5,2"-> R.string.a52
            "5,3"-> R.string.a53
            "5,4"-> R.string.a54
            "5,5"-> R.string.a55
            "5,6"-> R.string.a56
            "5,7"-> R.string.a57
            "5,8"-> R.string.a58
            "5,9"-> R.string.a59
            "6,1"-> R.string.a61
            "6,2"-> R.string.a62
            "6,3"-> R.string.a63
            "6,4"-> R.string.a64
            "6,5"-> R.string.a65
            "6,6"-> R.string.a66
            "6,7"-> R.string.a67
            "6,8"-> R.string.a68
            "6,9"-> R.string.a69
            "7,1"-> R.string.a71
            "7,2"-> R.string.a72
            "7,3"-> R.string.a73
            "7,4"-> R.string.a74
            "7,5"-> R.string.a75
            "7,6"-> R.string.a76
            "7,7"-> R.string.a77
            "7,8"-> R.string.a78
            "7,9"-> R.string.a79
            "8,1"-> R.string.a81
            "8,2"-> R.string.a82
            "8,3"-> R.string.a83
            "8,4"-> R.string.a84
            "8,5"-> R.string.a85
            "8,6"-> R.string.a86
            "8,7"-> R.string.a87
            "8,8"-> R.string.a88
            "8,9"-> R.string.a89
            "9,1"-> R.string.a91
            "9,2"-> R.string.a92
            "9,3"-> R.string.a93
            "9,4"-> R.string.a94
            "9,5"-> R.string.a95
            "9,6"-> R.string.a96
            "9,7"-> R.string.a97
            "9,8"-> R.string.a98
            "9,9"-> R.string.a99
            else -> R.string.a11

        }
        return retstr

    }

    private fun createControls(){


//        <com.google.android.material.textfield.TextInputEditText
//        android:id="@+id/a11"
//        android:textSize="35sp"
//        android:layout_weight="1"
//        android:hint="1,1"
//        android:gravity="center"
//        android:inputType="number"
//        android:background="@drawable/border"
//        android:fontFamily="sans-serif-medium"
//        android:layout_width="wrap_content"
//        android:layout_height="50dp"
//
//        tools:ignore="HardcodedText,TouchTargetSizeCheck" />

    }
}


