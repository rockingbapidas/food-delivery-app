package com.example.zomato.domain.model

data class Delivery(
    val id: String,
    val orderId: String,
    val status: DeliveryStatus,
    val deliveryAddress: String,
    val deliveryPersonName: String,
    val deliveryPersonPhone: String,
    val estimatedTime: String,
    val progress: Float,
    val restaurantName: String,
    val restaurantAddress: String,
    val items: List<DeliveryItem>,
    val currentLocation: Location
)

enum class DeliveryStatus {
    PICKING_UP,
    ON_THE_WAY,
    DELIVERED
}

data class DeliveryItem(
    val id: String,
    val name: String,
    val quantity: Int,
    val price: Double
)