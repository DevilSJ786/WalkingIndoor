package com.test.walkingindoor

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.test.walkingindoor.navigation.AppNavigation
import com.test.walkingindoor.ui.theme.WalkingIndoorTheme
import com.test.walkingindoor.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
         val PHYSICAL_ACTIVITY = 123
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED) {
            //ask for permission
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACTIVITY_RECOGNITION), PHYSICAL_ACTIVITY)
        }
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





