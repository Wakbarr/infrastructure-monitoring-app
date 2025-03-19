package com.example.infrastructure_monitoring_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.infrastructure_monitoring_app.data.dao.MonitoringDao
import com.example.infrastructure_monitoring_app.data.entities.MonitoringData

@Database(entities = [MonitoringData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun monitoringDao(): MonitoringDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "monitoring_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}