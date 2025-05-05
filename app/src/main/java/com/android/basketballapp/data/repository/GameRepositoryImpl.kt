package com.android.basketballapp.data.repository

import com.android.basketballapp.data.Game
import com.android.basketballapp.data.local.dao.GameDao
import com.android.basketballapp.data.remote.api.GameApi
import com.android.basketballapp.data.remote.mappers.toDomain
import com.android.basketballapp.data.remote.mappers.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.android.basketballapp.data.local.entity.GameEntity
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val api: GameApi,
    private val dao: GameDao
) : GameRepository {

    override fun getCachedGames(): Flow<List<Game>> {
        return dao.getAllGames().map { gameEntities ->
            gameEntities.map { entity ->
                entity.toDomain() // ✅ must return Game, not Unit
            }
        }
    }


    override suspend fun refreshGamesFromApi() {
        try {
            val response = api.getGames()
            val dtoList = response.data
            val entityList = dtoList.map { it.toEntity() }
            dao.insertGames(entityList)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
