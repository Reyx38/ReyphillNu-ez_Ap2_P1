package edu.com.reyphillnuez_ap2_p1.Presentation.Navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen{
    @Serializable
    data object Sistema: Screen()
    @Serializable
    data object List : Screen()
}