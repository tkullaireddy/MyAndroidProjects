package com.tadiaptri.csvtosqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(val context:Context):SQLiteOpenHelper(context,"contacts.db",null,1) {
    companion object{
        val TABLE_NAME:String = "CONTACT"
        val COL_0:String = "id"
        val COL_1:String = "name"
        val COL_2:String = "displayname"
        val COL_3:String = "mobileno"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query:String = "CREATE TABLE ${DbHelper.TABLE_NAME}(${DbHelper.COL_0} integer primary key autoincrement, ${DbHelper.COL_1} text, ${DbHelper.COL_2} text, ${DbHelper.COL_3} text);"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("Drop table ${DbHelper.TABLE_NAME}")
        onCreate(db)
    }
    fun insertData(values: ContentValues){
        // You need an SQLiteDatabase object with write permissions
        val db:SQLiteDatabase  = this.writableDatabase
        db.insert(DbHelper.TABLE_NAME,null,values)
    }

    fun loadData(): Cursor {
        val db:SQLiteDatabase = this.readableDatabase
        return db.rawQuery("select * from ${DbHelper.TABLE_NAME}",null,null)
    }

}