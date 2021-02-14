package com.example.giphytest.entity.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserReg")
class UserRegistration {

    @JvmField
    @PrimaryKey
    var id = 0

    @JvmField
    @ColumnInfo(name = "username")
    var username: String? = null

    @JvmField
    @ColumnInfo(name = "email")
    var email: String? = null

    @JvmField
    @ColumnInfo(name = "password")
    var password: String? = null

    @JvmField
    @ColumnInfo(name = "age")
    var age: String? = null
}