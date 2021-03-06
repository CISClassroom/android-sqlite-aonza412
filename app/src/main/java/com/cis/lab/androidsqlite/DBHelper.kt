package com.cis.lab.androidsqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    val DATABASE_NAME = "todo.db"
    val DATABASE_VERSION = 1
    val TABLE_NAME = "task"
    val COLUMN_ID = "id"
    val COLUMN_TASKNAME = "taskname"

    fun addTask( newTask: Task){
        val values=ContentValues()
        values.put(COLUMN_TASKNAME,newTask.taskname)

        val db = this.writableDatabase
        db.insert(TABLE_NAME,null,values)
        db.close()
    }
    fun getAllTask(): Cursor? {
        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM "+TABLE_NAME,null)
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " ( "+COLUMN_ID+" INTEGER PRIMARY KEY,"+
                COLUMN_TASKNAME+" TEXT )"
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val UPGRATE_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME
        db.execSQL(UPGRATE_TABLE)
        onCreate(db)
    }
}