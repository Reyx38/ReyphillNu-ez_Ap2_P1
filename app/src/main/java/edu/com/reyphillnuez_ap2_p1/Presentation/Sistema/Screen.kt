package edu.com.reyphillnuez_ap2_p1.Presentation.Sistema

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen{
    @Serializable
    data object Home: Screen()
    @Serializable
    data object List : Screen()
}