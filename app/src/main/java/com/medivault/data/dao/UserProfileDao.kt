package com.medivault.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.medivault.data.model.UserProfile
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the UserProfile entity
 */
@Dao
interface UserProfileDao {
    @Query("SELECT * FROM user_profiles WHERE userId = :userId")
    fun getUserById(userId: String): UserProfile?
    
    @Query("SELECT * FROM user_profiles WHERE userId = :userId")
    fun observeUserById(userId: String): Flow<UserProfile?>
    
    @Query("SELECT * FROM user_profiles")
    fun getAllUsers(): List<UserProfile>
    
    @Query("SELECT * FROM user_profiles")
    fun observeAllUsers(): Flow<List<UserProfile>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserProfile)
    
    @Update
    suspend fun updateUser(user: UserProfile)
    
    @Delete
    suspend fun deleteUser(user: UserProfile)
} 