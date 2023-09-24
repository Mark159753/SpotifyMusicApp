package com.example.data.until.update_time

interface UpdateTimeHelper {

    suspend fun canUpdate(key:String):Boolean

    suspend fun <T>canUpdate(key:Class<T>): Boolean

    suspend fun save(key: String)

    suspend fun <T>save(key:Class<T>)
}