package edu.com.reyphillnuez_ap2_p1.Presentation.Sistema

sealed interface Event {
    data class onChangeTareasId(val id: Int?): Event
    data class onChangeDescripcion(val descripcion: String): Event
    data class onChangeTiempo(val tiempo: Int?): Event
    data object Save: Event
    data object Delete: Event
    data object New: Event
}