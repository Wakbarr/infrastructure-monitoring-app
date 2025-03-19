package com.example.infrastructure_monitoring_app.repository

import com.example.infrastructure_monitoring_app.data.dao.MonitoringDao
import com.example.infrastructure_monitoring_app.data.entities.MonitoringData

class MonitoringRepository(private val monitoringDao: MonitoringDao) {
    suspend fun insert(monitoringData: MonitoringData): Long {
        return monitoringDao.insert(monitoringData)
    }

    suspend fun getAll(): List<MonitoringData> {
        return monitoringDao.getAll()
    }
}