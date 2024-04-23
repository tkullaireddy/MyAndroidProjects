package com.Zaxises.dbview
import android.content.Context
import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import java.lang.Exception
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException



class MyDatabaseHelper {

    private val driverClass = "net.sourceforge.jtds.jdbc.Driver"
    private val connectionString = "jdbc:jtds:sqlserver://sharedmssql5.znetindia.net, 1234/ZShareSeive"
    private val username = "ZShare"
    private val password = "Zaxises@2020"

    fun getConnection(context:Context): Connection? {
        try {
            Class.forName(driverClass)
            Toast.makeText(context,"Ok",Toast.LENGTH_LONG).show()
            val connection = DriverManager.getConnection(connectionString, username, password)
            Toast.makeText(context,"Ok",Toast.LENGTH_LONG).show()
            return connection
        } catch (e: Exception) {

            Toast.makeText(context,e.message.toString(),Toast.LENGTH_LONG).show()
            e.printStackTrace()
            return null
        }
    }
}

 class connectionClass() {


    private val ip = "sharedmssql5.znetindia.net, 1234" // your database server ip
    private val db = "ZShareSeive" // your database name
    private val username = "ZShare" // your database username
    private val password = "Zaxises@2020" // your database password


    fun dbConn(context:Context) : Connection? {

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var conn : Connection? = null
        var connString: String? = null
        try {
            Class.forName("net.sourgeforge.jtds.jdbc.Driver")
            connString = "jdbc:jtds:sqlserver://$ip;databaseName=$db;user=$username;password=$password;"
            Toast.makeText(context,"Before Con",Toast.LENGTH_SHORT).show()
            conn = DriverManager.getConnection(connString)
            Toast.makeText(context,"After Con",Toast.LENGTH_SHORT).show()
        }catch (ex: SQLException){
            Toast.makeText(context,"Error : " + ex.message.toString(),Toast.LENGTH_SHORT).show()
            Log.e("Error : ", ex.message.toString())

        }catch (ex1: ClassNotFoundException){
            Toast.makeText(context,"Error1: " + ex1.message.toString(),Toast.LENGTH_SHORT).show()
            Log.e("Error : ", ex1.message.toString())
        }catch (ex2: Exception){
            Toast.makeText(context,"Error2 : " + ex2.message.toString(),Toast.LENGTH_SHORT).show()
            Log.e("Error : ", ex2.message.toString())
        }
        finally {
            Toast.makeText(context,"Final",Toast.LENGTH_SHORT).show()
        }

        return conn
    }
}