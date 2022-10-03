package com.david.filmobil.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_history")
data class SearchHistoryModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "searchHistoryId")
    val id: Int = 0,
    @ColumnInfo(name = "searchHistoryQuery")
    val searchHistoryQuery: String,
    @ColumnInfo(name = "searchHistoryInsertionDate")
    val searchHistoryInsertionDate: Long
)
