package com.example.dbmyapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper (context: Context) :
    SQLiteOpenHelper(context,"University",null,1){

    companion object {
        const val TABLE_NAME = "Results"
        private const val SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + "(id TEXT," + "course_1 TEXT ,credit_1 INTEGER,grade_1 Text ,course_2 TEXT ,credit_2 INTEGER,grade_2 Text ,course_3 TEXT ,credit_3 INTEGER,grade_3 Text )"
        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME

    }


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    fun insertData(
        id: String?,
        course1: String?,
        credit1: Int,
        grade1: String?,
        course2: String?,
        credit2: Int,
        grade2: String?,
        course3: String?,
        credit3: Int,
        grade3: String?
    ) {
        val values = ContentValues()
        values.put("id", id)
        values.put("course_1", course1)
        values.put("credit_1", credit1)
        values.put("grade_1", grade1)
        values.put("course_2", course2)
        values.put("credit_2", credit2)
        values.put("grade_2", grade2)
        values.put("course_3", course3)
        values.put("credit_3", credit3)
        values.put("grade_3", grade3)

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
    }


}