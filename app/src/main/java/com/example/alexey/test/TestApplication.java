package com.example.alexey.test;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.alexey.test.db.AppDataBase;

/**
 * Created by alexey on 23.07.18.
 */

public class TestApplication extends Application {

    private static TestApplication instance;

    private AppDataBase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDataBase.class, "database")
                .build();
    }

    public static TestApplication getInstance() {
        return instance;
    }

    public AppDataBase getDatabase() {
        return database;
    }
}
