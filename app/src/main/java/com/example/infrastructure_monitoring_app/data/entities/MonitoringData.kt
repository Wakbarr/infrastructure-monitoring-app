package com.example.infrastructure_monitoring_app.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "monitoring_data")
data class MonitoringData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tanggal: String,
    val namaOperator: String,
    val noHpOperator: String,
    val namaVendor: String,
    val regionalPage1: String,
    val regionalPage2: String,
    val distrik: String,
    val jenisAlatBerat: String,
    val merkTypeAlat: String,
    val status: String,
    val kondisi: String,
    val afdeling: String,
    val blok: String,
    val objekKerja: String,
    val pekerjaan: String,
    val jamKerja: String,
    val fotoJktAwal: String,
    val bbm: String,
    val fotoBonBbm: String,
    val realisasiKerja: String,
    val kendalaOperasional: String,
    val dokumentasiSebelum: String,
    val dokumentasiSesudah: String,
    val jamStart: String,
    val jamMulaiBekerja: String,
    val jamSelesaiBekerja: String,
    val jamStop: String
)
