package com.example.foodies.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodies.data.local.entity.CatalogDBO

@Dao
interface CatalogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCatalog(catalog: CatalogDBO)

    @Query("SELECT * FROM CatalogDBO")
    suspend fun getCatalog(): CatalogDBO?
}