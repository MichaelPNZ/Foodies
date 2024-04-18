package com.example.foodies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodies.data.local.dao.CatalogDao
import com.example.foodies.data.local.dao.ShoppingCartDao
import com.example.foodies.data.local.entity.CatalogDBO
import com.example.foodies.data.local.entity.ShoppingCartDBO
import com.example.foodies.data.local.entity.StringListConverter

@Database(
    entities = [CatalogDBO::class, ShoppingCartDBO::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val catalogDao: CatalogDao
    abstract val shoppingCartDao: ShoppingCartDao
}