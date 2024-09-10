package com.shashank.ekacare.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.shashank.ekacare.data.local.entity.EkaCareUser
import kotlinx.coroutines.flow.Flow

@Dao
interface EkaCareUserDao {

    @Upsert
    suspend fun addUser(ekaCareUser: EkaCareUser)

    @Query("SELECT * FROM EkaCareUser ORDER BY id ASC")
    fun getEkaCareUserList(): Flow<List<EkaCareUser>>
}