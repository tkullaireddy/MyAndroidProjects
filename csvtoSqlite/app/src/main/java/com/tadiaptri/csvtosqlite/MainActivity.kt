package com.tadiaptri.csvtosqlite

import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream
import java.util.stream.Stream as UtilStreamStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        val btn = findViewById<Button>(R.id.btnLoad)
        val btn1 = findViewById<Button>(R.id.btnLoad1)
        val txt = findViewById<TextView>(R.id.textView)

        var helper:DbHelper = DbHelper(this)

        btn.setOnClickListener {
//            val csv = File("app/sampledata/contacts.csv")



            val csv1 ="ATP DEE S. Yogesh Babu,,ATP DEE S. Yogesh Babu,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP IOP P. Sai Prasad,,ATP IOP P. Sai Prasad,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP IOP K. Srinivasulu,,ATP IOP K. Srinivasulu,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP IOP V. Venkata Ramana,,ATP IOP V. Venkata Ramana,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP SI P. Phanindranath Reddy,,ATP SI P. Phanindranath Reddy,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP SI G. Jayapal Reddy,,ATP SI G. Jayapal Reddy,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP SI P. Seshagiri,,ATP SI P. Seshagiri,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP ACTO D. Vijaya Lakshmi,,ATP ACTO D. Vijaya Lakshmi,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP ACTO B. Suresh Kumar,,ATP ACTO B. Suresh Kumar,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP AEE T. Rajasekhar,,ATP AEE T. Rajasekhar,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP AEE T. Kullai Reddy,,ATP AEE T. Kullai Reddy,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP AEE L. Ravindranath,,ATP AEE L. Ravindranath,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP AO J. Vasu Prakash,,ATP AO J. Vasu Prakash,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP AG H.M. Siva Prasad,,ATP AG H.M. Siva Prasad,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP Supdt C. Siva Kumar Sarma,,ATP Supdt C. Siva Kumar Sarma,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP SA B. Venkatesu,,ATP SA B. Venkatesu,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP SA P. Naga Jyothi,,ATP SA P. Naga Jyothi,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP JA M. Sudarshan Babu,,ATP JA M. Sudarshan Babu,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP Comp Opr S. Usman Basha,,ATP Comp Opr S. Usman Basha,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP Comp Opr R. Radha Krishna,,ATP Comp Opr R. Radha Krishna,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP HC G. Eswaraiah,,ATP HC G. Eswaraiah,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP HC Y. Marenna,,ATP HC Y. Marenna,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP PC R. Thateppa,,ATP PC R. Thateppa,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP PC G. Janardhanappa,,ATP PC G. Janardhanappa,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP PC Y. Mukkanneswar,,ATP PC Y. Mukkanneswar,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP PC K.N. Mahendranath Reddy,,ATP PC K.N. Mahendranath Reddy,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP PC B. Manohar Reddy,,ATP PC B. Manohar Reddy,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP PC S. Gangaraju,,ATP PC S. Gangaraju,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP PC G. Vijay Kumar,,ATP PC G. Vijay Kumar,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP PC K. Chandra,,ATP PC K. Chandra,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP PC N. Veeraiah,,ATP PC N. Veeraiah,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP HG S. Manoranjan,,ATP HG S. Manoranjan,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP HG S. Sreeramulu,,ATP HG S. Sreeramulu,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n" +
                    "ATP HG M.C. Venkatesh Naik,,ATP HG M.C. Venkatesh Naik,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n"

            val csv = csv1.split("\n")
            for (line in csv) {





//            csv.forEachLine { line ->

                val items = line.split(',')
                val values: ContentValues = ContentValues()
                values.put(DbHelper.COL_1, items[0])
                values.put(DbHelper.COL_2, items[2])
                values.put(DbHelper.COL_3, "+91-9876543210")

                helper.insertData(values)
                // println(items)
            }

            txt .text = "Completed"
        }

        btn1.setOnClickListener {
            val c: Cursor = helper.loadData()
            // ITs time to extract the data and display
            txt.text = ""
            c.moveToFirst()
            do {
                val id1 = c.getInt(0)
                val n1 = c.getString(1)
                val a1 = c.getInt(2)
                val a2 = c.getInt(3)
                txt.append("${id1} ${n1} ${a1} ${a1}\n")
            } while (c.moveToNext())
        }

    }
}