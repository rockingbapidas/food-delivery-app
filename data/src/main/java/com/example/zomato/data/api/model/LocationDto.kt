package com.example.zomato.data.api.model

import com.example.zomato.domain.model.Location

data class LocationDto(
    val latitude: Double,
    val longitude: Double,
    val lastUpdated: Long
) {
    fun toDomain() = Location(
        latitude, longitude, lastUpdated
    )

    companion object {
        fun fromDomain(location: Location) = LocationDto(
            latitude = location.latitude,
            longitude = location.longitude,
            lastUpdated = location.lastUpdated
        )
    }
}
