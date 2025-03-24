package com.medivault

import android.app.Application
import androidx.room.Room
import com.medivault.data.MediVaultDatabase

class MediVaultApp : Application() {
    
    companion object {
        lateinit var database: MediVaultDatabase
            private set
    }
    
    override fun onCreate() {
        super.onCreate()
        
        // Initialize Room database
        database = Room.databaseBuilder(
            applicationContext,
            MediVaultDatabase::class.java,
            "medivault_database"
        ).build()
    }
} 