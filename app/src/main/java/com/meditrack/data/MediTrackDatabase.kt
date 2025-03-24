package com.meditrack.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.meditrack.data.dao.UserProfileDao
import com.meditrack.data.model.UserProfile
import com.meditrack.data.util.DateConverters

/**
 * Main database class for the MediTrack application.
 * 
 * This abstract class defines the database configuration and serves as the main access point
 * for the underlying connection. It brings together all the entities and their corresponding
 * DAOs, ensuring a centralized database architecture.
 * 
 * The database is set to export schema for version control, allowing us to track schema
 * changes over time. It also uses type converters to handle complex data types like Date.
 */
@Database(
    entities = [
        UserProfile::class,
        // More entities will be added as we develop
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(DateConverters::class)
abstract class MediTrackDatabase : RoomDatabase() {
    /**
     * Provides access to the UserProfileDao for user profile operations.
     * 
     * @return An implementation of UserProfileDao
     */
    abstract fun userProfileDao(): UserProfileDao
    
    // Additional DAOs will be added as we develop more features
} 