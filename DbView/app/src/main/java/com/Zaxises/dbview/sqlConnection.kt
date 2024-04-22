package com.Zaxises.dbview
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

    fun getConnection(): Connection? {
        try {
            Class.forName(driverClass)
            val connection = DriverManager.getConnection(connectionString, username, password)
            return connection
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}

class connectionClass {

    private val ip = "sharedmssql5.znetindia.net, 1234" // your database server ip
    private val db = "ZShareSeive" // your database name
    private val username = "ZShare" // your database username
    private val password = "Zaxises@2020" // your database password

    fun dbConn() : Connection? {

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var conn : Connection? = null
        var connString: String? = null
        try {
            Class.forName("net.sourgeforge.jtds.jdbc.Driver")
            connString = "jdbc:jtds:sqlserver://$ip;databaseName=$db;user=$username;password=$password;"
            conn = DriverManager.getConnection(connString)
        }catch (ex: SQLException){
            Log.e("Error : ", ex.message.toString())

        }catch (ex1: ClassNotFoundException){
            Log.e("Error : ", ex1.message.toString())
        }catch (ex2: Exception){
            Log.e("Error : ", ex2.message.toString())
        }

        return conn
    }
}