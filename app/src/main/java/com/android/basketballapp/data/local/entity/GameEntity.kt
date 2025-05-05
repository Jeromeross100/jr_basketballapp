package com.android.basketballapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey val id: Int,
    val homeTeam: String,
    val visitorTeam: String,
    val homeScore: Int,
    val visitorScore: Int,
    val date: String,
    val season: Int,
    val period: Int,
    val status: String,
    val time: String,
    val isPostseason: Boolean
)
