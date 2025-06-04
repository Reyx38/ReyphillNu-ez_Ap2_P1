package edu.com.reyphillnuez_ap2_p1.Data.Local.entities

import androidx.room.Entity

@Entity(tableName = "Tareas")
data class TareasEntity (
    val tareaId: Int? = null,
    val descripcion: String? = null,
    val tiempo: Int? = null
)