package edu.com.reyphillnuez_ap2_p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.com.reyphillnuez_ap2_p1.Presentation.Navigation.HostNavigation
import edu.com.reyphillnuez_ap2_p1.ui.theme.ReyphillNuñez_Ap2_P1Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReyphillNuñez_Ap2_P1Theme {
                val nav = rememberNavController()
                HostNavigation(nav)

            }
        }
    }
}
