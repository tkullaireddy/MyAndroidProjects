package com.nareshittechnologies.uicomponents

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState!=null && savedInstanceState.containsKey("Name"))
        {
            updateUserData(savedInstanceState)
        }

    }

    var data1= mutableMapOf<String,String>()

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        readData(outState)
    }




    private fun readData(outState: Bundle){
        val pn:TextInputLayout = findViewById(R.id.person_name)
        val pa:TextInputLayout = findViewById(R.id.person_age)
        val result:TextView = findViewById(R.id.result)

        val gm:RadioButton=findViewById(R.id.female)
        var g:String = "Male"
        if (gm.isChecked) g="Female"
        outState.putString("Name",pn.editText?.text.toString())
        outState.putString("Age",pa.editText?.text.toString())
        outState.putString("Gender",g)
        outState.putString("result",result.text.toString())
    }


    private fun getTxtView(id: Int): TextInputEditText {
        val textInputLayout = findViewById<TextInputLayout>(id)
        return textInputLayout.editText as TextInputEditText
    }

    private fun updateUserData(outState: Bundle){

        val pn:TextInputEditText = getTxtView(R.id.person_name)
        val pa:TextInputEditText = getTxtView(R.id.person_age)
        val result:TextView = findViewById(R.id.result)
        val rbm: RadioButton = findViewById(R.id.male)
        val rbf:RadioButton=findViewById(R.id.female)

        pn.setText( outState.getString("Name"))
        pa.setText(outState.getString("Age"))
        result.text=outState.getString("result")
        if (outState.getString("Gender") =="Male") rbm.isChecked=true
        else rbf.isChecked=true

    }
    // This function is called after filling the data on the other views and button is clicked.
    fun registerWithData(view: View) {

        val pn:TextInputLayout = findViewById(R.id.person_name)
        val pa:TextInputLayout = findViewById(R.id.person_age)


        val gm:RadioButton=findViewById(R.id.female)
        var g:String = "Male"
        if (gm.isChecked) g="Female"

        val result:TextView = findViewById(R.id.result)
        result.text = "${pn.editText?.text.toString()}  ${pa.editText?.text.toString()} $g"

    }
}