package com.android.basketballapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.basketballapp.data.local.dao.GameDao
import com.android.basketballapp.data.local.entity.GameEntity

@Database(
    entities = [GameEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}
