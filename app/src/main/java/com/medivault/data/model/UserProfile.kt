package com.medivault.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * Entity representing a user profile in the database
 */
@Entity(tableName = "user_profiles")
data class UserProfile(
    @PrimaryKey
    val userId: String,
    val name: String,
    val email: String?,
    val phone: String?,
    val dateOfBirth: Date?,
    val bloodType: String?,
    val allergies: String?,
    val emergencyContact: String?,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
) 