package com.medivault.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.medivault.data.dao.UserProfileDao
import com.medivault.data.model.UserProfile
import com.medivault.data.util.DateConverters

@Database(
    entities = [UserProfile::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverters::class)
abstract class MediVaultDatabase : RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDao
} 