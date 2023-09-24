package com.example.localdata.proto.user

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.example.spotifymusicapp.LocalUser
import java.io.InputStream
import java.io.OutputStream

object UserSerializer: Serializer<LocalUser> {

    override val defaultValue: LocalUser
        get() = LocalUser.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): LocalUser {
        try {
            return LocalUser.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: LocalUser, output: OutputStream) = t.writeTo(output)
}