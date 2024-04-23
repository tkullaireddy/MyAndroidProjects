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
import java.util.concurrent.ExecutorService

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button.setOnClickListener {

//            var result =  executeQuery1("select * from tblUser")
//
//            Toast.makeText(this, "Result", Toast.LENGTH_SHORT).show()
//            //Toast.makeText(applicationContext, result!!, Toast.LENGTH_LONG).show()
//
//

            Toast.makeText(this,"Clicked", Toast.LENGTH_LONG).show()

// Example usage:
            val mySqlConnection = MySQLConnection()
            val connection = mySqlConnection.getConnection(this)
            if (connection != null) {
                // Connection successful, perform database operations here
                // Remember to close the connection when done
                Toast.makeText(this,"Sucess", Toast.LENGTH_LONG).show()
                connection.close()
            } else {
                // Connection failed
                Toast.makeText(this,"Failed", Toast.LENGTH_LONG).show()
            }




        }





    }




    fun executeQuery1(query: String): ResultSet? {
        val cc =connectionClass()
        Toast.makeText(this,"Before Connection " ,Toast.LENGTH_SHORT).show()
        val connection =  cc.dbConn(this   )

        Toast.makeText(this,"Connection ${connection.toString()}" ,Toast.LENGTH_SHORT).show()

        try {
            val statement = connection?.createStatement()
            val resultSet = statement?.executeQuery(query)
            return resultSet
        } catch (e: Exception) {
            Toast.makeText(this,"Exception ${e.message.toString()}" ,Toast.LENGTH_LONG).show()
            e.printStackTrace()
            return null
        } finally {
            connection?.close() // Close connection after use
        }
    }

    fun executeQuery(query: String): ResultSet? {
        val connection = MyDatabaseHelper().getConnection(this) ?: return null

        Toast.makeText(this,connection.isClosed.toString(),Toast.LENGTH_LONG).show()

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