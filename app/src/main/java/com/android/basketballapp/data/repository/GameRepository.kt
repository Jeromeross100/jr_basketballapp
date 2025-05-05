package com.android.basketballapp.data.repository

import com.android.basketballapp.data.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getCachedGames(): Flow<List<Game>>
    suspend fun refreshGamesFromApi()
}
