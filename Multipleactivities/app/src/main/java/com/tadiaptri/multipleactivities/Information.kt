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

    private fun GetInformation(aName:String, aAge:Int ):String{
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val yearBirth = currentYear - aAge
        val typeofAge = if (aAge>45) "You are born in $yearBirth, you've accumulated a wealth of experience and wisdom, making you a respected senior figure.\n Your maturity and dedication have undoubtedly led you to acquire a vast amount of knowledge, which is truly impressive."
        else if (aAge>33) "You are Born in $yearBirth, you've entered the vibrant stage of middle age, bringing with it both experience and maturity.\n Your talents are undeniable, adding another layer of dimension to your life."
        else if (aAge>24) "You are born in $yearBirth, you embody a youthful spirit while demonstrating continuous growth in various aspects.\n This evolution makes you a truly wonderful companion."
        else "You are born in $yearBirth, you've exciting opportunities to explore and grow in many directions. With an open mind and a willingness to learn, you can discover a wealth of knowledge and experiences.\n Enjoy the journey!"

        return  " Hi $aName,\n\n $typeofAge"
    }
}