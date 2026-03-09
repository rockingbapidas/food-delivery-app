package com.example.zomato.data.api

import com.example.zomato.data.api.model.RestaurantDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestaurantApi {
    @GET("restaurants")
    suspend fun getRestaurants(): List<RestaurantDto>

    @GET("restaurants/{id}")
    suspend fun getRestaurantById(@Path("id") id: String): RestaurantDto

    @GET("restaurants/search")
    suspend fun searchRestaurants(@Query("query") query: String): List<RestaurantDto>

    @GET("restaurants/cuisine/{cuisine}")
    suspend fun getRestaurantsByCuisine(@Path("cuisine") cuisine: String): List<RestaurantDto>

    @GET("restaurants/nearby")
    suspend fun getRestaurantsByLocation(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("radius") radius: Double
    ): List<RestaurantDto>
} 