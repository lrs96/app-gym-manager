package br.com.app_modelo_mobile.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteTransactionListener
import android.os.Parcel
import android.os.Parcelable

class LoginRepository(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    var BD_NOME:String = "App_mobile"
    var BD_VERSAO:Int = 1



    init {
        LoginRepository(context, BD_NOME, null, BD_VERSAO)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       var query:StringBuffer = StringBuffer()
        query.append("CREATE TABLE TB_LOGIN( ")
        query.append(" ID_LOGIN INT PRIMARY KEY AUTO_INCREMENT,")
        query.append(" USUARIO TEXT(15) NOT NULL,")
        query.append(" SENHA TEXT(15) NOT NULL)")

        db?.execSQL(query.toString())

    }

    override fun onCreate(db: SQLiteDatabase?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    fun popularBD(){
        var query:StringBuffer = StringBuffer()
        query.append("INSERT INTO TB_LOGIN(USUARIO, SENHA) VALUES(?, ?)")

        var db:SQLiteDatabase = writableDatabase
        var params:String[]

        db.execSQL(query.toString(), )
    }
}