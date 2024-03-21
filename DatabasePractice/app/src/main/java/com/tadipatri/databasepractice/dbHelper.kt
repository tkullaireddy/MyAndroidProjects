package com.tadipatri.databasepractice

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class dbHelper( context: Context):SQLiteOpenHelper(context,"Kullai.db",null,1) {
    companion object {
        val TABLE_NAME: String = "Employee"
        val emp_id: String = "emp_id"
        val emp_name: String = "emp_name"
        val emp_desig: String = "emp_designation"
        val emp_address: String = "emp_address"
        val emp_salary: String = "emp_salary"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        val query: String =
            "CREATE TABLE ${dbHelper.TABLE_NAME}(${dbHelper.emp_id} integer primary key autoincrement, ${dbHelper.emp_name} text, ${dbHelper.emp_desig} text, ${dbHelper.emp_address} text, ${dbHelper.emp_salary} integer);"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("Drop table ${dbHelper.TABLE_NAME}")
        onCreate(db)

    }

    fun insertData(values: ContentValues) {
        // You need an SQLiteDatabase object with write permissions
        val db: SQLiteDatabase = this.writableDatabase
        db.insert(dbHelper.TABLE_NAME, null, values)
    }

    fun loadData(): Cursor {
        val db: SQLiteDatabase = this.readableDatabase
        return db.rawQuery("select * from ${dbHelper.TABLE_NAME}", null, null)
    }

    fun executegivenQuery(strQuery: String): Cursor {
        val db: SQLiteDatabase = this.writableDatabase
        return db.rawQuery(strQuery, null, null)
    }

}