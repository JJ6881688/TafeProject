package com.example.newtafehrdds22023

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase


import android.database.sqlite.SQLiteOpenHelper

class Dbhandler(context: Context, factory: SQLiteDatabase.CursorFactory?)
    :SQLiteOpenHelper(context,DATABASE_NAME,factory,DB_VERSION) {

        companion object{
            const val DATABASE_NAME = "HRManager.db"
            const val DB_VERSION = 2
        }
        private val tableName:String = "PERSON"
        private val keyID:String="ID"
        private val keyName:String="NAME"
        private val keyMobile:String = "MOBILE"
        private val keyEmail:String="EMAIL"
        private val keyAddress:String="ADDRESS"
        private val keyImageFile:String="IMAGEFILE"

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $tableName ($keyID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$keyName TEXT, $keyImageFile TEXT, $keyAddress TEXT, $keyMobile TEXT, $keyEmail TEXT)"
        db.execSQL(createTable)
        val cv = ContentValues()
        cv.put(keyName, "`Jack Lin")
        cv.put(keyMobile, "0487437109")
        cv.put(keyAddress, "Sydney")
        cv.put(keyImageFile, "first")
        cv.put(keyEmail, "disheng.lin2@studytafensw.edu.au")
        db.insert(tableName, null,cv)

    }

    override fun onUpgrade(db: SQLiteDatabase, old: Int, new: Int) {

        db.execSQL("DROP TABLE IF EXISTS $tableName")
        onCreate(db)
    }

    fun getAllPersons():ArrayList<Person>{
        val personList = ArrayList<Person>()
        val selectQuery =  "SELECT * FROM $tableName"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if(cursor.moveToFirst()){
            do{
                val person = Person()
                person.id = cursor.getInt(0)
                person.name = cursor.getString(1)
                person.imageFile = cursor.getString(2)
                person.address = cursor.getString(3)
                person.mobile = cursor.getString(4)
                person.email = cursor.getString(5)

                personList.add(person)
            }while(cursor.moveToNext())
            }
        cursor.close()
        db.close()
        return personList

    }

    fun getPerson(id:Int): Person {
        val db = this.readableDatabase
        val person = Person()
        val cursor = db.query(tableName,
            arrayOf(keyID,keyName,keyImageFile,keyAddress,keyMobile,keyEmail),
            "$keyID=?",
            arrayOf(id.toString()),
             null,
            null,
            null)
        if(cursor!=null){
            cursor.moveToFirst()
            person.id = cursor.getInt(0)
            person.name = cursor.getString(1)
            person.imageFile = cursor.getString(2)
            person.address = cursor.getString(3)
            person.mobile = cursor.getString(4)
            person.email = cursor.getString(5)


        }

        cursor.close()
        db.close()
        return person
    }

    fun updatePerson(person: Person) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(keyName,person.name)
        cv.put(keyImageFile,person.imageFile)
        cv.put(keyAddress,person.address)
        cv.put(keyMobile,person.mobile)
        cv.put(keyEmail,person.email)

        db.update(tableName,cv,"$keyID=?", arrayOf(person.id.toString()))
        db.close()

    }

    fun addPerson(person: Person) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(keyName,person.name)
        cv.put(keyImageFile,person.imageFile)
        cv.put(keyAddress,person.address)
        cv.put(keyMobile,person.mobile)
        cv.put(keyEmail,person.email)

        db.insert(tableName,null,cv)
        db.close()
    }

    fun deletePerson(id: Int) {
        val db = this.writableDatabase
        db.delete(tableName,"$keyID=?", arrayOf(id.toString()))
        db.close()
    }


}