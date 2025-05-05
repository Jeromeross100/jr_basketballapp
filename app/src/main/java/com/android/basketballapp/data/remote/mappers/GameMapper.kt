package com.android.basketballapp.data.remote.mappers

import com.android.basketballapp.data.Game
import com.android.basketballapp.data.local.entity.GameEntity

fun GameEntity.toDomain(): Game {
    return Game(
        id = id,
        date = date,
        homeTeamName = homeTeam,
        visitorTeamName = visitorTeam,
        homeTeamScore = homeScore,
        visitorTeamScore = visitorScore,
        season = season,
        period = period,
        status = status,
        time = time,
        isPostseason = isPostseason
    )
}
