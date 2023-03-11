package com.test.walkingindoor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.test.walkingindoor.navigation.AppNavigation
import com.test.walkingindoor.screens.home.HomeViewModel
import com.test.walkingindoor.ui.theme.WalkingIndoorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                AppNavigation( hiltViewModel<HomeViewModel>())
            }
        }
    }
}

@Composable
fun MyApp(context: @Composable () -> Unit) {
    WalkingIndoorTheme {
        context()
    }
}




