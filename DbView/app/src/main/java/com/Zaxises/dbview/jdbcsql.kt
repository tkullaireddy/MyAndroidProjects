package com.Zaxises.dbview

import android.content.Context
import android.widget.Toast
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class MySQLConnection {

    fun getConnection(context:Context): Connection? {
        var connection: Connection? = null
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver")

            // Replace 'your_database_url', 'your_username', and 'your_password' with your actual database connection details
            val url = "jdbc:mysql://sharedmssql5.znetindia.net, 1234/ZShareSeive"
            val username = "ZShare"
            val password = "Zaxises@2020"

            // Establish the connection
            connection = DriverManager.getConnection(url, username, password)
        } catch (e: ClassNotFoundException) {
            Toast.makeText(context,"Error: ${e.message}",Toast.LENGTH_LONG).show()
            e.printStackTrace()
        } catch (e: SQLException) {
            Toast.makeText(context,"Error1: ${e.message}",Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
        return connection
    }
}
