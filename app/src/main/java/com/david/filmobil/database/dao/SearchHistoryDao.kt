package com.david.filmobil.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.david.filmobil.database.entities.SearchHistoryModel

@Dao
interface SearchHistoryDao {
    @Query("SELECT * FROM search_history WHERE (:searchQuery IS NULL OR `searchHistoryQuery` GLOB '*' || :searchQuery|| '*' ) ORDER BY searchHistoryInsertionDate DESC")
    fun getAllSearchHistory(searchQuery: String? = null): List<SearchHistoryModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntoSearchHistory(searchHistoryModel: SearchHistoryModel)

    @Delete
    suspend fun deleteFromSearchHistory(searchHistoryModel: SearchHistoryModel)
}