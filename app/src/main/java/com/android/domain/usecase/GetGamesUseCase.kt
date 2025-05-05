package com.android.domain.usecase

import com.android.basketballapp.data.Game
import com.android.basketballapp.data.repository.GameRepository
import com.android.basketballapp.data.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val repository: GameRepository
) {
    operator fun invoke(): Flow<ApiResult<List<Game>>> {
        return repository.getCachedGames()
            .map { games -> ApiResult.Success(games) as ApiResult<List<Game>> }
            .onStart { emit(ApiResult.Loading) }
            .catch { e -> emit(ApiResult.Error(e.message ?: "Unknown error")) }
    }
}
