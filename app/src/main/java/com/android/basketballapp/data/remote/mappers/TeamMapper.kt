package com.android.basketballapp.data.remote.mappers

import com.android.basketballapp.data.Team

fun TeamDto.toDomain(): Team {
    return Team(
        id = id,
        name = name.orEmpty(),
        fullName = fullName.orEmpty(),
        city = city.orEmpty(),
        conference = conference.orEmpty(),
        division = division.orEmpty(),
        abbreviation = abbreviation.orEmpty()
    )
}
