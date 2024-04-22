package com.Zaxises.dbview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.Zaxises.dbview.databinding.ActivityMainBinding
import java.sql.Connection
import java.sql.ResultSet

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button.setOnClickListener {

            var result =  executeQuery("select * from tblUser")

            Toast.makeText(this, result.toString(), Toast.LENGTH_LONG).show()
            //Toast.makeText(applicationContext, result!!, Toast.LENGTH_LONG).show()
        }





    }


    fun executeQuery(query: String): ResultSet? {
        val connection = MyDatabaseHelper().getConnection() ?: return null

        try {
            val statement = connection.createStatement()
            val resultSet = statement.executeQuery(query)
            return resultSet
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        } finally {
            connection?.close() // Close connection after use
        }
    }


}