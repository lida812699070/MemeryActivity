package com.example.memeryactivity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.memeryactivity.databinding.ActivityMain5Binding

class MainActivity5DataStore : AppCompatActivity() {

    private var binding: ActivityMain5Binding? = null
    private val DATASTORE_NAME = "hy_datastore"
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "dataStore_data")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain5Binding.inflate(layoutInflater)
        setContentView(binding?.root)
        this.dataStore.data
    }
}