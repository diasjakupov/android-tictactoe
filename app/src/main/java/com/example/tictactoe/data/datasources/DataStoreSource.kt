package com.example.tictactoe.data.datasources

import android.content.Context
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class DataStoreSource(private val context:Context) {
    object PreferencesKey{
        val USER_ID = intPreferencesKey("userId")
        val TOKEN = stringPreferencesKey("userToken")
        const val DATA_STORE_NAME = "gamePreferences"
    }

    private val Context.dataStore by preferencesDataStore(
        PreferencesKey.DATA_STORE_NAME
    )

    val userId: Flow<Int> = context.dataStore.data.map {
        it[PreferencesKey.USER_ID]?: -1
    }

    val userToken:Flow<String> = context.dataStore.data.map {
        it[PreferencesKey.TOKEN]?: ""
    }

    suspend fun updateUserId(userId:Int=1){
        context.dataStore.edit {
            it[PreferencesKey.USER_ID]=userId
        }
    }

    suspend fun updateUserToken(token:String=""){
        context.dataStore.edit {
            it[PreferencesKey.TOKEN]=token
        }
    }
}