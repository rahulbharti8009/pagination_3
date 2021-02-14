package com.example.giphytest.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.giphytest.entity.room.DummyUser;
import com.example.giphytest.entity.room.DummyUserDao;
import com.example.giphytest.entity.room.UserRegistration;

@Database(entities =  {UserRegistration.class, DummyUser.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract DummyUserDao userDao();

}
