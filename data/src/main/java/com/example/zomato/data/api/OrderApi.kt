package com.example.zomato.data.api

import com.example.zomato.data.api.model.OrderDto
import retrofit2.http.*

interface OrderApi {
    @GET("orders")
    suspend fun getOrders(): List<OrderDto>

    @GET("orders/{id}")
    suspend fun getOrderById(@Path("id") id: String): OrderDto

    @GET("orders/active")
    suspend fun getActiveOrders(): List<OrderDto>

    @GET("orders/history")
    suspend fun getOrderHistory(): List<OrderDto>

    @POST("orders")
    suspend fun placeOrder(@Body order: OrderDto): OrderDto

    @DELETE("orders/{id}")
    suspend fun cancelOrder(@Path("id") id: String)

    @PUT("orders/{id}/status")
    suspend fun updateOrderStatus(
        @Path("id") id: String,
        @Query("status") status: String
    )

    @GET("orders/{id}/track")
    suspend fun trackOrder(@Path("id") id: String): OrderDto

    @POST("orders/{id}/rate")
    suspend fun rateOrder(
        @Path("id") id: String,
        @Query("rating") rating: Int,
        @Query("feedback") feedback: String?
    )
} 