package com.example.giphytest.entity.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
class DummyUser {
    @JvmField
    @PrimaryKey
    var uid = 0

    @JvmField
    @ColumnInfo(name = "name")
    var name: String? = null

    @JvmField
    @ColumnInfo(name = "age")
    var age: String? = null
}