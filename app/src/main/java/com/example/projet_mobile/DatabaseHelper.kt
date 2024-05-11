package com.example.projet_mobile
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
         const val DATABASE_VERSION = 1
         const val DATABASE_NAME = "School"
         const val TABLE_NAME = "Enseignant"
         const val COLUMN_EMAIL = "Email"
         const val COLUMN_USERNAME = "Username"
         const val COLUMN_PASSWORD = "Mot_de_passe"

    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_EMAIL VARCHAR(255) NOT NULL PRIMARY KEY, " +
                "$COLUMN_USERNAME VARCHAR(50) NOT NULL, " +
                "$COLUMN_PASSWORD VARCHAR(255) NOT NULL)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val dropTable = "DROP TABLE IF EXISTS $TABLE_NAME"
        db.execSQL(dropTable)
        onCreate(db)
    }

}
