package com.meditrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.meditrack.ui.screens.MediTrackApp
import com.meditrack.ui.theme.MediTrackTheme

/**
 * Main entry point Activity for the MediTrack application.
 * 
 * This activity serves as the container for the Jetpack Compose UI. It sets up the theme
 * and the root composable for the application. The UI is built entirely with Jetpack Compose,
 * eliminating the need for traditional XML layouts.
 */
class MainActivity : ComponentActivity() {
    /**
     * Called when the activity is first created. This is where we initialize the UI
     * using Jetpack Compose.
     * 
     * @param savedInstanceState If the activity is being re-initialized after previously 
     * being shut down, this contains the data it most recently supplied in onSaveInstanceState.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediTrackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MediTrackApp()
                }
            }
        }
    }
} 