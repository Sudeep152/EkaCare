package com.shashank.ekacare.data.local.repository

import com.shashank.ekacare.data.local.dao.EkaCareUserDao
import com.shashank.ekacare.data.local.entity.EkaCareUser
import com.shashank.ekacare.domain.repository.EkaCareUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EkaCareUserRepositoryImpl @Inject constructor (private val dao: EkaCareUserDao) :
    EkaCareUserRepository {
    override suspend fun addUser(ekaCareUser: EkaCareUser) {
        dao.addUser(ekaCareUser)
    }

    override fun getEkaCareUserList(): Flow<List<EkaCareUser>> {
        return dao.getEkaCareUserList()
    }
}