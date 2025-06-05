package edu.com.reyphillnuez_ap2_p1.Presentation.Navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen{
    @Serializable
    data class Tarea(val id: Int?): Screen()
    @Serializable
    data object List : Screen()
}