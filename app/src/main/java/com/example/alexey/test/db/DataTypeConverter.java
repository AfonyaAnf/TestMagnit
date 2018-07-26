package com.example.alexey.test.db;

import android.arch.persistence.room.TypeConverter;

import com.example.alexey.test.model.entity.GeoPointEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by alexey on 23.07.18.
 */

public class DataTypeConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static GeoPointEntity stringToGeoPoint(String data) {
        Type listType = new TypeToken<GeoPointEntity>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String geoPointToString(GeoPointEntity geoPointEntity) {
        return gson.toJson(geoPointEntity);
    }
}
