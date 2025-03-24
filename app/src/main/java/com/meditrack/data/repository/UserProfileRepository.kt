package com.meditrack.data.repository

import com.meditrack.data.dao.UserProfileDao
import com.meditrack.data.model.UserProfile
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for managing UserProfile data.
 * 
 * This repository provides a clean API for the rest of the application to interact
 * with UserProfile data. It abstracts away the details of data sources (in this case, 
 * a local database through Room) and provides a unified interface for data operations.
 * 
 * This class follows the Repository pattern and is designed to be the single source
 * of truth for UserProfile data within the application.
 */
@Singleton
class UserProfileRepository @Inject constructor(
    private val userProfileDao: UserProfileDao
) {
    /**
     * Inserts a new user profile into the database.
     * 
     * @param userProfile The profile to insert
     * @return The ID of the newly inserted profile
     */
    suspend fun insertUserProfile(userProfile: UserProfile): Long {
        return userProfileDao.insertUserProfile(userProfile)
    }
    
    /**
     * Updates an existing user profile.
     * 
     * @param userProfile The profile to update
     */
    suspend fun updateUserProfile(userProfile: UserProfile) {
        userProfileDao.updateUserProfile(userProfile)
    }
    
    /**
     * Deletes a user profile.
     * 
     * @param userProfile The profile to delete
     */
    suspend fun deleteUserProfile(userProfile: UserProfile) {
        userProfileDao.deleteUserProfile(userProfile)
    }
    
    /**
     * Gets a user profile by its ID.
     * 
     * @param id The ID of the profile to retrieve
     * @return The user profile, or null if not found
     */
    suspend fun getUserProfileById(id: Long): UserProfile? {
        return userProfileDao.getUserProfileById(id)
    }
    
    /**
     * Gets the main user profile (the profile marked as the primary user).
     * 
     * @return The main user profile, or null if not found
     */
    suspend fun getMainUserProfile(): UserProfile? {
        return userProfileDao.getMainUserProfile()
    }
    
    /**
     * Gets all user profiles, sorted by first name.
     * 
     * @return A Flow emitting the list of all user profiles
     */
    fun getAllUserProfiles(): Flow<List<UserProfile>> {
        return userProfileDao.getAllUserProfiles()
    }
    
    /**
     * Searches for user profiles by first or last name.
     * 
     * @param searchQuery The search string to find in names
     * @return A Flow emitting the list of matching profiles
     */
    fun searchUserProfiles(searchQuery: String): Flow<List<UserProfile>> {
        return userProfileDao.searchUserProfiles(searchQuery)
    }
} 