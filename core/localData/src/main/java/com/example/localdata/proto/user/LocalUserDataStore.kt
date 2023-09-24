package com.example.localdata.proto.user

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.spotifymusicapp.LocalUser
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private const val DATA_STORE_FILE_NAME = "user_prefs.pb"

private val Context.userPreferencesStore: DataStore<LocalUser> by dataStore(
    fileName = DATA_STORE_FILE_NAME,
    serializer = UserSerializer
)

class LocalUserDataStore @Inject constructor(
    @ApplicationContext
    private val context: Context
) {

    private val TAG: String = "LocalUserDataStore"

    val userPreferencesFlow: Flow<UserData?> = context.userPreferencesStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error reading sort order preferences.", exception)
                emit(LocalUser.getDefaultInstance())
            } else {
                throw exception
            }
        }
        .map { user ->
            if (user.id.isNullOrBlank())
                null
            else{
                UserData(
                    id = user.id,
                    name = user.displayName,
                    email = user.email
                )
            }
        }

    suspend fun updateUser(user: UserData) {
        context.userPreferencesStore.updateData { preferences ->
            preferences
                .toBuilder()
                .setId(user.id)
                .setDisplayName(user.name)
                .setEmail(user.email)
                .build()
        }
    }

    suspend fun clear() {
        context.userPreferencesStore.updateData { preferences ->
            preferences.toBuilder().clear().build()
        }
    }
}

data class UserData(
    val id:String,
    val name:String,
    val email:String
)