package edu.com.reyphillnuez_ap2_p1.Presentation.Sistema

import edu.com.reyphillnuez_ap2_p1.Data.Local.entities.TareasEntity

data class UiState (
    val tareasId: Int? = null,
    val descripcion: String? = null,
    val errorDescripcion: String? = null,
    val tiempo: Int? = null,
    val errorTiempo: String? = null,
    val tareas: List<TareasEntity> = emptyList()
)