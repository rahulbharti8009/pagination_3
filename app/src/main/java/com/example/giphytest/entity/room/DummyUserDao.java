package com.example.giphytest.entity.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public
interface DummyUserDao {

    @Query("SELECT * FROM User")
    List<DummyUser> getAllUser();

    @Query("SELECT * FROM User WHERE name LIKE :first AND age LIKE :last LIMIT 1")
    DummyUser findByName(String first , String last );

    @Insert
    void insertAll(UserRegistration users );

    @Delete
    void delete(DummyUser user);

}
