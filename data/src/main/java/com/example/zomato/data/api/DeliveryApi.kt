package com.example.zomato.data.api

import com.example.zomato.data.api.model.DeliveryDto
import retrofit2.http.*

interface DeliveryApi {
    @GET("deliveries/current")
    suspend fun getCurrentDelivery(): DeliveryDto

    @GET("deliveries")
    suspend fun getDeliveries(): List<DeliveryDto>

    @GET("deliveries/{id}")
    suspend fun getDeliveryById(@Path("id") id: String): DeliveryDto

    @PUT("deliveries/{id}/status")
    suspend fun updateDeliveryStatus(
        @Path("id") id: String,
        @Query("status") status: String
    )

    @POST("deliveries/{id}/contact")
    suspend fun contactDeliveryPerson(@Path("id") id: String)

    @POST("deliveries/{id}/rate")
    suspend fun rateDelivery(
        @Path("id") id: String,
        @Query("rating") rating: Int,
        @Query("feedback") feedback: String?
    )
} 