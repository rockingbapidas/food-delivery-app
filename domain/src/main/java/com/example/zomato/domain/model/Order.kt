package com.example.zomato.domain.model

data class Order(
    val id: String,
    val restaurantId: String,
    val restaurantName: String,
    val items: List<OrderItem>,
    val totalAmount: Double,
    val status: OrderStatus,
    val orderTime: Long,
    val estimatedDeliveryTime: Long,
    val deliveryAddress: String,
    val customerName: String,
    val customerPhone: String,
    val specialInstructions: String?,
    val paymentMethod: PaymentMethod,
    val deliveryPerson: DeliveryPerson
)

data class OrderItem(
    val id: String,
    val name: String,
    val quantity: Int,
    val price: Double,
    val specialInstructions: String?
)

data class DeliveryPerson(
    val id: String,
    val name: String,
    val phone: String,
    val currentLocation: Location?,
    val estimatedArrivalTime: Long?
)

enum class OrderStatus(val status: String) {
    PENDING("Pending"),
    ACCEPTED("Accepted"),
    PREPARING("Preparing"),
    READY_FOR_PICKUP("Ready for Pickup"),
    OUT_FOR_DELIVERY("Out for Delivery"),
    DELIVERED("Delivered"),
    CANCELLED("Cancelled")
}

enum class PaymentMethod(val method: String) {
    CASH_ON_DELIVERY("Cash on Delivery"),
    ONLINE_PAYMENT("Online Payment"),
    WALLET("Wallet"),
    UPI("UPI")
}
