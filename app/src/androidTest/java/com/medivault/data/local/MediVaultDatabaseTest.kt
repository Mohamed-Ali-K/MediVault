package com.medivault.data.local

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.Date

@Entity(tableName = "medical_records")
data class MedicalRecordEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val type: String,
    val title: String,
    val date: Long,
    val doctorName: String?,
    val description: String?,
    val attachments: List<String> = emptyList()
)

@Dao
interface MedicalRecordDao {
    @Query("SELECT * FROM medical_records WHERE id = :id")
    suspend fun getRecordById(id: String): MedicalRecordEntity?
    
    @Query("SELECT * FROM medical_records WHERE userId = :userId")
    fun getRecordsByUserId(userId: String): Flow<List<MedicalRecordEntity>>
    
    @Query("SELECT * FROM medical_records WHERE userId = :userId AND type = :type")
    fun getRecordsByType(userId: String, type: String): Flow<List<MedicalRecordEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: MedicalRecordEntity)
    
    @Delete
    suspend fun deleteRecord(record: MedicalRecordEntity): Int
    
    @Query("DELETE FROM medical_records WHERE id = :id")
    suspend fun deleteRecordById(id: String): Int
}

@Database(entities = [MedicalRecordEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MediVaultDatabase : RoomDatabase() {
    abstract fun medicalRecordDao(): MedicalRecordDao
}

class Converters {
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return value.joinToString(",")
    }
    
    @TypeConverter
    fun toStringList(value: String): List<String> {
        return if (value.isEmpty()) emptyList() else value.split(",")
    }
}

@RunWith(AndroidJUnit4::class)
class MediVaultDatabaseTest {
    private lateinit var medicalRecordDao: MedicalRecordDao
    private lateinit var db: MediVaultDatabase
    
    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, MediVaultDatabase::class.java
        ).allowMainThreadQueries().build()
        medicalRecordDao = db.medicalRecordDao()
    }
    
    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
    
    @Test
    fun insertAndGetRecord() = runBlocking {
        val testRecord = MedicalRecordEntity(
            id = "test123",
            userId = "user1",
            type = "DIAGNOSIS",
            title = "Test Record",
            date = Date().time,
            doctorName = "Dr. Test",
            description = "Test description",
            attachments = listOf("test.pdf")
        )
        
        medicalRecordDao.insertRecord(testRecord)
        
        val retrievedRecord = medicalRecordDao.getRecordById("test123")
        assertEquals(testRecord, retrievedRecord)
    }
    
    @Test
    fun getUserRecords() = runBlocking {
        // Insert multiple records for different users
        val user1Record1 = MedicalRecordEntity(
            id = "rec1",
            userId = "user1",
            type = "DIAGNOSIS",
            title = "User 1 Record 1",
            date = Date().time,
            doctorName = "Dr. One",
            description = "Test description 1"
        )
        
        val user1Record2 = MedicalRecordEntity(
            id = "rec2",
            userId = "user1",
            type = "LAB_RESULT",
            title = "User 1 Record 2",
            date = Date().time,
            doctorName = "Dr. Two",
            description = "Test description 2"
        )
        
        val user2Record = MedicalRecordEntity(
            id = "rec3",
            userId = "user2",
            type = "PRESCRIPTION",
            title = "User 2 Record",
            date = Date().time,
            doctorName = "Dr. Three",
            description = "Test description 3"
        )
        
        medicalRecordDao.insertRecord(user1Record1)
        medicalRecordDao.insertRecord(user1Record2)
        medicalRecordDao.insertRecord(user2Record)
        
        // Get records for user1
        val user1Records = medicalRecordDao.getRecordsByUserId("user1").first()
        assertEquals(2, user1Records.size)
        assertTrue(user1Records.contains(user1Record1))
        assertTrue(user1Records.contains(user1Record2))
        
        // Get records for user2
        val user2Records = medicalRecordDao.getRecordsByUserId("user2").first()
        assertEquals(1, user2Records.size)
        assertTrue(user2Records.contains(user2Record))
    }
    
    @Test
    fun getRecordsByType() = runBlocking {
        // Insert records with different types
        val diagnosisRecord = MedicalRecordEntity(
            id = "diag1",
            userId = "user1",
            type = "DIAGNOSIS",
            title = "Diagnosis Record",
            date = Date().time,
            doctorName = "Dr. Diagnosis",
            description = "Diagnosis description"
        )
        
        val labRecord = MedicalRecordEntity(
            id = "lab1",
            userId = "user1",
            type = "LAB_RESULT",
            title = "Lab Result Record",
            date = Date().time,
            doctorName = "Dr. Lab",
            description = "Lab result description"
        )
        
        val prescriptionRecord = MedicalRecordEntity(
            id = "presc1",
            userId = "user1",
            type = "PRESCRIPTION",
            title = "Prescription Record",
            date = Date().time,
            doctorName = "Dr. Prescription",
            description = "Prescription description"
        )
        
        medicalRecordDao.insertRecord(diagnosisRecord)
        medicalRecordDao.insertRecord(labRecord)
        medicalRecordDao.insertRecord(prescriptionRecord)
        
        // Get records by type
        val diagnosisRecords = medicalRecordDao.getRecordsByType("user1", "DIAGNOSIS").first()
        assertEquals(1, diagnosisRecords.size)
        assertEquals(diagnosisRecord, diagnosisRecords[0])
        
        val labRecords = medicalRecordDao.getRecordsByType("user1", "LAB_RESULT").first()
        assertEquals(1, labRecords.size)
        assertEquals(labRecord, labRecords[0])
        
        val prescriptionRecords = medicalRecordDao.getRecordsByType("user1", "PRESCRIPTION").first()
        assertEquals(1, prescriptionRecords.size)
        assertEquals(prescriptionRecord, prescriptionRecords[0])
    }
    
    @Test
    fun deleteRecord() = runBlocking {
        val testRecord = MedicalRecordEntity(
            id = "toDelete",
            userId = "user1",
            type = "DIAGNOSIS",
            title = "Record to Delete",
            date = Date().time,
            doctorName = "Dr. Delete",
            description = "Delete me"
        )
        
        medicalRecordDao.insertRecord(testRecord)
        
        // Verify record exists
        val insertedRecord = medicalRecordDao.getRecordById("toDelete")
        assertEquals(testRecord, insertedRecord)
        
        // Delete by entity
        val deleteResult = medicalRecordDao.deleteRecord(testRecord)
        assertEquals(1, deleteResult)
        
        // Verify record was deleted
        val deletedRecord = medicalRecordDao.getRecordById("toDelete")
        assertNull(deletedRecord)
    }
    
    @Test
    fun deleteRecordById() = runBlocking {
        val testRecord = MedicalRecordEntity(
            id = "toDeleteById",
            userId = "user1",
            type = "DIAGNOSIS",
            title = "Record to Delete by ID",
            date = Date().time,
            doctorName = "Dr. DeleteById",
            description = "Delete me by ID"
        )
        
        medicalRecordDao.insertRecord(testRecord)
        
        // Verify record exists
        val insertedRecord = medicalRecordDao.getRecordById("toDeleteById")
        assertEquals(testRecord, insertedRecord)
        
        // Delete by ID
        val deleteResult = medicalRecordDao.deleteRecordById("toDeleteById")
        assertEquals(1, deleteResult)
        
        // Verify record was deleted
        val deletedRecord = medicalRecordDao.getRecordById("toDeleteById")
        assertNull(deletedRecord)
    }
} 