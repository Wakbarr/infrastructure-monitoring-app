package com.example.infrastructure_monitoring_app.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.infrastructure_monitoring_app.data.entities.MonitoringData

@Dao
interface MonitoringDao {
    @Insert
    suspend fun insert(monitoringData: MonitoringData): Long

    @Query("SELECT * FROM monitoring_data")
    suspend fun getAll(): List<MonitoringData>

    @Query("DELETE FROM monitoring_data")
    suspend fun deleteAll(): Int
}
