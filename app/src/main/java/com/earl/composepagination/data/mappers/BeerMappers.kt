package com.earl.composepagination.data.mappers

import com.earl.composepagination.data.local.BeerEntity
import com.earl.composepagination.data.remote.BeerDto
import com.earl.composepagination.domain.Beer

fun BeerDto.toBeerEntity(): BeerEntity {
    return BeerEntity(
        id =  id,
        name = name,
        tagline = tagline,
        firstBrewed = first_brewed,
        description = description,
        imageUrl = image_url
    )
}

fun BeerEntity.toBeer(): Beer{
    return Beer(
        id =  id,
        name = name,
        tagline = tagline,
        firstBrewed = firstBrewed,
        description = description,
        imageUrl = imageUrl
    )
}