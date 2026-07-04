package com.finderbar.hilsa.core.di

import android.content.Context
import androidx.room.Room
import com.finderbar.hilsa.core.database.HilsaDatabase
import com.finderbar.hilsa.core.database.dao.UserDao
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
    fun provideDatabase(@ApplicationContext context: Context): HilsaDatabase {
        return Room.databaseBuilder(
            context,
            HilsaDatabase::class.java,
            "hilsa_db"
        ).build()
    }

    @Provides
    fun provideUserDao(database: HilsaDatabase): UserDao {
        return database.userDao()
    }
}