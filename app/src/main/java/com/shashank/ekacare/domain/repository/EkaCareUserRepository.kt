package com.shashank.ekacare.domain.repository

import com.shashank.ekacare.data.local.dao.EkaCareUserDao
import com.shashank.ekacare.data.local.entity.EkaCareUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface EkaCareUserRepository {
    suspend fun addUser(ekaCareUser: EkaCareUser)
    fun getEkaCareUserList(): Flow<List<EkaCareUser>>
}