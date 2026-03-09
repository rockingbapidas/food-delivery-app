package com.example.zomato.data.api

import com.example.zomato.data.api.model.MenuItemDto
import retrofit2.http.*

interface MenuApi {
    @GET("restaurants/{restaurantId}/menu")
    suspend fun getMenuItems(@Path("restaurantId") restaurantId: String): List<MenuItemDto>

    @GET("restaurants/{restaurantId}/menu/{menuItemId}")
    suspend fun getMenuItemById(
        @Path("restaurantId") restaurantId: String,
        @Path("menuItemId") menuItemId: String
    ): MenuItemDto

    @POST("restaurants/{restaurantId}/menu")
    suspend fun addMenuItem(
        @Path("restaurantId") restaurantId: String,
        @Body menuItem: MenuItemDto
    ): MenuItemDto

    @PUT("restaurants/{restaurantId}/menu/{menuItemId}")
    suspend fun updateMenuItem(
        @Path("restaurantId") restaurantId: String,
        @Path("menuItemId") menuItemId: String,
        @Body menuItem: MenuItemDto
    ): MenuItemDto

    @DELETE("restaurants/{restaurantId}/menu/{menuItemId}")
    suspend fun deleteMenuItem(
        @Path("restaurantId") restaurantId: String,
        @Path("menuItemId") menuItemId: String
    )
}
