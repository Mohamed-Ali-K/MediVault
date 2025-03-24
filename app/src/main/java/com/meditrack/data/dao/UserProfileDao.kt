package com.meditrack.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.meditrack.data.model.UserProfile
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the UserProfile entity.
 * 
 * This interface provides methods to interact with the user_profiles table
 * in the database. It supports basic CRUD operations and search functionality.
 * All operations are designed to work with Kotlin Coroutines for asynchronous execution.
 */
@Dao
interface UserProfileDao {
    /**
     * Inserts a new user profile into the database.
     * If a profile with the same ID already exists, it will be replaced.
     * 
     * @param userProfile The user profile to insert
     * @return The new row ID for the inserted profile
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProfile(userProfile: UserProfile): Long
    
    /**
     * Updates an existing user profile in the database.
     * 
     * @param userProfile The user profile to update
     */
    @Update
    suspend fun updateUserProfile(userProfile: UserProfile)
    
    /**
     * Deletes a user profile from the database.
     * 
     * @param userProfile The user profile to delete
     */
    @Delete
    suspend fun deleteUserProfile(userProfile: UserProfile)
    
    /**
     * Retrieves a specific user profile by ID.
     * 
     * @param id The ID of the user profile to retrieve
     * @return The user profile with the given ID, or null if not found
     */
    @Query("SELECT * FROM user_profiles WHERE id = :id")
    suspend fun getUserProfileById(id: Long): UserProfile?
    
    /**
     * Retrieves the main user profile (if any).
     * There should only be one profile with isMainProfile set to true.
     * 
     * @return The main user profile, or null if not found
     */
    @Query("SELECT * FROM user_profiles WHERE isMainProfile = 1 LIMIT 1")
    suspend fun getMainUserProfile(): UserProfile?
    
    /**
     * Retrieves all user profiles, sorted by first name.
     * 
     * @return A Flow emitting the list of all user profiles
     */
    @Query("SELECT * FROM user_profiles ORDER BY firstName ASC")
    fun getAllUserProfiles(): Flow<List<UserProfile>>
    
    /**
     * Searches for user profiles by first name or last name.
     * 
     * @param searchQuery The search string to find in first or last names
     * @return A Flow emitting the list of matching user profiles
     */
    @Query("SELECT * FROM user_profiles WHERE firstName LIKE '%' || :searchQuery || '%' OR lastName LIKE '%' || :searchQuery || '%'")
    fun searchUserProfiles(searchQuery: String): Flow<List<UserProfile>>
} 