package com.android.basketballapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.basketballapp.data.local.entity.GameEntity // ✅ YOUR Room entity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Query("SELECT * FROM games ORDER BY date DESC")
    fun getAllGames(): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(games: List<com.android.basketballapp.data.local.entity.GameEntity>)

    @Query("DELETE FROM games")
    suspend fun clearGames()
}
