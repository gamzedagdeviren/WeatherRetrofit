package com.kotlin.weatherretrofit.db

import androidx.room.*

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateInsert()
}