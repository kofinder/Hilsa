package com.finderbar.hilsa.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.finderbar.hilsa.core.database.dao.UserDao
import com.finderbar.hilsa.core.database.model.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class HilsaDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}