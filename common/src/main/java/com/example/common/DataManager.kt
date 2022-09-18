package com.example.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "cashstore")
class DataManager (val context: Context){

    val title = stringPreferencesKey("title")
    val image = stringPreferencesKey("image")

    suspend fun saveProduct(product: Product){
        context.dataStore.edit { pref ->
            pref[title] = product.title
            pref[image] = product.image
        }
    }

    val readProduct : Flow<Product> = context.dataStore.data.map { prefs ->
        Product(
            image = prefs[image] ?: "",
             title = prefs[title] ?: ""
        )

    }
}

data class Product(
    val image : String,
    val title : String
)