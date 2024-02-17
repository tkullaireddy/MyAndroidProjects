package com.tadiaptri.multipleactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.Calendar


class Information : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        val name:String = intent.extras?.getString("NAME").toString()
        val age:Int = intent.extras?.getString("AGE").toString().toInt()

        findViewById<TextView>(R.id.textView).text=GetInformation(name,age)
}

    fun GetInformation(aName:String, aAge:Int ):String{
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val yearBirth = currentYear - aAge
        var typeofAge = "Young Aged"
        if (aAge>45) typeofAge = "Senior Person,\n You have a great maturity,\n You has achieved great Knowledge"
        else if (aAge>33) typeofAge = "Middle Aged Person,\n You have a great maturity,\n You have  great talents"
        else if (aAge>24) typeofAge = "Young Aged Person,\n You have evolving in all aspects,\n You are a great companion."
        else  typeofAge = "Too Young Person,\n You are too young to understand everything,\n You have a great opportunities  to grow in every possible direction and lot to explore"

        return  " Hi $aName,\n You are born in $yearBirth \n You are a $typeofAge"
    }
}