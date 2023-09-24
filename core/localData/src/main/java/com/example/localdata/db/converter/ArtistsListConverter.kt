package com.example.localdata.db.converter

import androidx.room.TypeConverter
import com.example.localdata.db.entities.Artist
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ArtistsListConverter {

    @TypeConverter
    fun fromStringArrayList(value: List<Artist>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringArrayList(value: String): List<Artist> {
        return try {
            val listType = object :TypeToken<List<Artist>>(){}.type
            Gson().fromJson(value, listType)
        } catch (e: Exception) {
            arrayListOf()
        }
    }
}