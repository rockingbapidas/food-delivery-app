package com.example.zomato.data.api

import com.example.zomato.data.api.model.CartItemDto
import retrofit2.http.*

interface CartApi {
    @GET("cart")
    suspend fun getCartItems(): List<CartItemDto>

    @POST("cart/items")
    suspend fun addToCart(
        @Query("menuItemId") menuItemId: String,
        @Query("quantity") quantity: Int = 1
    ): CartItemDto

    @PUT("cart/items/{menuItemId}")
    suspend fun updateQuantity(
        @Path("menuItemId") menuItemId: String,
        @Query("quantity") quantity: Int
    ): CartItemDto

    @DELETE("cart/items/{menuItemId}")
    suspend fun removeFromCart(@Path("menuItemId") menuItemId: String)

    @DELETE("cart")
    suspend fun clearCart()

    @POST("cart/place-order")
    suspend fun placeOrder(): String // Returns order ID
} 