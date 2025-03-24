package com.meditrack.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * Entity class representing a user profile in the application.
 * This can be the primary user or family members.
 * 
 * This entity stores basic personal information, contact details,
 * emergency contact information, and health-related information
 * for each user profile in the application.
 *
 * The [isMainProfile] flag is used to identify the primary user's profile.
 */
@Entity(tableName = "user_profiles")
data class UserProfile(
    /**
     * Unique identifier for the profile, auto-generated by Room.
     */
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    // Basic personal information
    /**
     * First name of the user.
     */
    val firstName: String,
    
    /**
     * Last name of the user.
     */
    val lastName: String,
    
    /**
     * Date of birth of the user.
     */
    val dateOfBirth: Date,
    
    /**
     * Gender of the user.
     */
    val gender: String,
    
    /**
     * Blood type of the user. Can be null if unknown.
     */
    val bloodType: String? = null,
    
    /**
     * Height of the user in centimeters. Can be null if unknown.
     */
    val height: Float? = null,  // in cm
    
    /**
     * Weight of the user in kilograms. Can be null if unknown.
     */
    val weight: Float? = null,  // in kg
    
    // Contact information
    /**
     * Phone number of the user. Can be null.
     */
    val phone: String? = null,
    
    /**
     * Email address of the user. Can be null.
     */
    val email: String? = null,
    
    /**
     * Physical address of the user. Can be null.
     */
    val address: String? = null,
    
    // Emergency contact
    /**
     * Name of the emergency contact person. Can be null.
     */
    val emergencyContactName: String? = null,
    
    /**
     * Phone number of the emergency contact person. Can be null.
     */
    val emergencyContactPhone: String? = null,
    
    /**
     * Relationship of the emergency contact person to the user. Can be null.
     */
    val emergencyContactRelation: String? = null,
    
    // Additional information
    /**
     * Any allergies the user has. Can be null.
     */
    val allergies: String? = null,
    
    /**
     * Any chronic conditions the user has. Can be null.
     */
    val chronicConditions: String? = null,
    
    /**
     * Additional notes about the user's health. Can be null.
     */
    val notes: String? = null,
    
    // Metadata
    /**
     * Flag indicating if this is the main user profile.
     * There should only be one profile with this flag set to true.
     */
    val isMainProfile: Boolean = false,
    
    /**
     * Timestamp when the profile was created.
     */
    val createdAt: Date = Date(),
    
    /**
     * Timestamp when the profile was last updated.
     */
    val updatedAt: Date = Date()
) 