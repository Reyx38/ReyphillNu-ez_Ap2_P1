package edu.com.reyphillnuez_ap2_p1.Data.Local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tareas")
data class TareasEntity (
    @PrimaryKey
    val tareaId: Int? = null,
    val descripcion: String? = null,
    val tiempo: Int? = null
)