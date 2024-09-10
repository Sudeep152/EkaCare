package com.shashank.ekacare.di

import android.content.Context
import androidx.room.Room
import com.shashank.ekacare.data.local.dao.EkaCareUserDao
import com.shashank.ekacare.data.local.database.EkaCareUserRoomDatabase
import com.shashank.ekacare.data.local.repository.EkaCareUserRepositoryImpl
import com.shashank.ekacare.domain.repository.EkaCareUserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): EkaCareUserRoomDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            EkaCareUserRoomDatabase::class.java,
            "eka_care_database"
        ).build()
    }

    @Provides
    fun provideUserDao(database: EkaCareUserRoomDatabase): EkaCareUserDao {
        return database.ekaCareUserDao()
    }

    @Provides
    @Singleton
    fun provideEkaCareUserRepository(dao: EkaCareUserDao): EkaCareUserRepository {
        return EkaCareUserRepositoryImpl(dao)
    }
}