package com.shashank.ekacare.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shashank.ekacare.data.local.dao.EkaCareUserDao
import com.shashank.ekacare.data.local.entity.EkaCareUser

@Database(entities = [EkaCareUser::class], version = 1, exportSchema = false)
abstract class EkaCareUserRoomDatabase : RoomDatabase() {
    abstract fun ekaCareUserDao(): EkaCareUserDao
}