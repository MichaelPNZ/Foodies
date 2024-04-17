package com.example.foodies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodies.data.local.dao.CatalogDao
import com.example.foodies.data.local.entity.CatalogDBO
import com.example.foodies.data.local.entity.StringListConverter

@Database(
    entities = [CatalogDBO::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val catalogDao: CatalogDao
}