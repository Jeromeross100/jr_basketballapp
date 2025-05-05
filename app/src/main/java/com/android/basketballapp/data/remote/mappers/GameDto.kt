package com.android.basketballapp.data.remote.mappers

import com.android.basketballapp.data.Game
import com.android.basketballapp.data.local.entity.GameEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameDto(
    val id: Int,
    val date: String,
    @Json(name = "home_team") val homeTeam: TeamDto,
    @Json(name = "home_team_score") val homeTeamScore: Int,
    @Json(name = "visitor_team") val visitorTeam: TeamDto,
    @Json(name = "visitor_team_score") val visitorTeamScore: Int,
    val season: Int,
    val period: Int,
    val status: String,
    val time: String?, // Nullable field
    val postseason: Boolean
)

// ✅ Converts GameDto to GameEntity (API → Room)
fun GameDto.toEntity(): GameEntity {
    return GameEntity(
        id = id,
        homeTeam = homeTeam.fullName,
        visitorTeam = visitorTeam.fullName,
        homeScore = homeTeamScore,
        visitorScore = visitorTeamScore,
        date = date,
        season = season,
        period = period,
        status = status,
        time = time ?: "", // fallback for null
        isPostseason = postseason
    )
}
