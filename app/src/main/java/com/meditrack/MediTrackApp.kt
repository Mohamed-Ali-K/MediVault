package com.meditrack

import android.app.Application
import androidx.room.Room
import com.meditrack.data.MediTrackDatabase

/**
 * Main Application class for MediTrack.
 * 
 * This class serves as the entry point for the application and is responsible for
 * initializing app-wide components and resources. It manages the lifecycle of
 * singleton objects like the Room database.
 * 
 * The class extends Android's Application class and is referenced in the manifest file.
 */
class MediTrackApp : Application() {
    /**
     * The single instance of the Room database for the application.
     * It is initialized lazily during onCreate and accessible throughout the app.
     */
    lateinit var database: MediTrackDatabase
        private set
    
    /**
     * Called when the application is starting, before any activity, service,
     * or receiver objects have been created.
     * 
     * This is where we initialize the Room database.
     */
    override fun onCreate() {
        super.onCreate()
        
        // Initialize Room database
        database = Room.databaseBuilder(
            applicationContext,
            MediTrackDatabase::class.java,
            "meditrack_database"
        ).build()
    }
} 