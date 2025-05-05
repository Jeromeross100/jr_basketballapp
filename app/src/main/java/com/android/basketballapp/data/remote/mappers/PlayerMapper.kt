package com.android.basketballapp.data.remote.mappers

import com.android.basketballapp.data.Player

fun PlayerDto.toDomain(): Player {
    val fullName = "${first_name.orEmpty()} ${last_name.orEmpty()}".trim()

    val height = if (height_feet != null && height_inches != null) {
        "${height_feet}'${height_inches}\""
    } else {
        "N/A"
    }

    val weight = weight_pounds?.toString() ?: "N/A"

    return Player(
        id = id,
        fullName = if (fullName.isNotBlank()) fullName else "Unknown Player",
        position = position ?: "Unknown",
        height = height,
        weight = weight,
        teamName = team.fullName
    )
}
