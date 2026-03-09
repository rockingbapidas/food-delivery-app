package com.example.zomato.data.api.model

import com.example.zomato.domain.model.Delivery
import com.example.zomato.domain.model.DeliveryItem
import com.example.zomato.domain.model.DeliveryStatus

data class DeliveryDto(
    val id: String,
    val orderId: String,
    val status: String,
    val deliveryAddress: String,
    val deliveryPersonName: String,
    val deliveryPersonPhone: String,
    val estimatedTime: String,
    val progress: Float,
    val restaurantName: String,
    val restaurantAddress: String,
    val items: List<DeliveryItemDto>,
    val currentLocation: LocationDto
) {
    fun toDomain(): Delivery {
        return Delivery(
            id = id,
            orderId = orderId,
            status = DeliveryStatus.valueOf(status),
            deliveryAddress = deliveryAddress,
            deliveryPersonName = deliveryPersonName,
            deliveryPersonPhone = deliveryPersonPhone,
            estimatedTime = estimatedTime,
            progress = progress,
            restaurantName = restaurantName,
            restaurantAddress = restaurantAddress,
            items = items.map { it.toDomain() },
            currentLocation = currentLocation.toDomain()
        )
    }

    companion object {
        fun fromDomain(delivery: Delivery): DeliveryDto {
            return DeliveryDto(
                id = delivery.id,
                orderId = delivery.orderId,
                status = delivery.status.name,
                deliveryAddress = delivery.deliveryAddress,
                deliveryPersonName = delivery.deliveryPersonName,
                deliveryPersonPhone = delivery.deliveryPersonPhone,
                estimatedTime = delivery.estimatedTime,
                progress = delivery.progress,
                restaurantName = delivery.restaurantName,
                restaurantAddress = delivery.restaurantAddress,
                items = delivery.items.map { DeliveryItemDto.fromDomain(it) },
                currentLocation = LocationDto.fromDomain(delivery.currentLocation)
            )
        }
    }
}

data class DeliveryItemDto(
    val id: String,
    val name: String,
    val quantity: Int,
    val price: Double
) {
    fun toDomain(): DeliveryItem {
        return DeliveryItem(
            id = id,
            name = name,
            quantity = quantity,
            price = price
        )
    }

    companion object {
        fun fromDomain(item: DeliveryItem): DeliveryItemDto {
            return DeliveryItemDto(
                id = item.id,
                name = item.name,
                quantity = item.quantity,
                price = item.price
            )
        }
    }
}