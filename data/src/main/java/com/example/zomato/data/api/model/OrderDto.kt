package com.example.zomato.data.api.model

import com.example.zomato.domain.model.DeliveryPerson
import com.example.zomato.domain.model.Order
import com.example.zomato.domain.model.OrderItem
import com.example.zomato.domain.model.OrderStatus
import com.example.zomato.domain.model.PaymentMethod

data class OrderDto(
    val id: String,
    val restaurantId: String,
    val restaurantName: String,
    val items: List<OrderItemDto>,
    val totalAmount: Double,
    val deliveryFee: Double = 0.0,
    val status: String,
    val orderTime: Long,
    val estimatedDeliveryTime: Long,
    val deliveryDate: String? = null,
    val deliveryAddress: String,
    val customerName: String,
    val customerPhone: String,
    val specialInstructions: String?,
    val paymentMethod: String,
    val deliveryPerson: DeliveryPersonDto,
    val rating: Double? = null
) {
    fun toDomain(): Order {
        return Order(
            id = id,
            restaurantId = restaurantId,
            restaurantName = restaurantName,
            items = items.map { it.toDomain() },
            totalAmount = totalAmount,
            deliveryFee = deliveryFee,
            status = OrderStatus.valueOf(status),
            orderTime = orderTime,
            estimatedDeliveryTime = estimatedDeliveryTime,
            deliveryDate = deliveryDate,
            deliveryAddress = deliveryAddress,
            customerName = customerName,
            customerPhone = customerPhone,
            specialInstructions = specialInstructions,
            paymentMethod = PaymentMethod.valueOf(paymentMethod),
            deliveryPerson = deliveryPerson.toDomain(),
            rating = rating
        )
    }

    companion object {
        fun fromDomain(order: Order): OrderDto {
            return OrderDto(
                id = order.id,
                restaurantId = order.restaurantId,
                restaurantName = order.restaurantName,
                items = order.items.map { OrderItemDto.fromDomain(it) },
                totalAmount = order.totalAmount,
                deliveryFee = order.deliveryFee,
                status = order.status.name,
                orderTime = order.orderTime,
                estimatedDeliveryTime = order.estimatedDeliveryTime,
                deliveryDate = order.deliveryDate,
                deliveryAddress = order.deliveryAddress,
                customerName = order.customerName,
                customerPhone = order.customerPhone,
                specialInstructions = order.specialInstructions,
                paymentMethod = order.paymentMethod.name,
                deliveryPerson = order.deliveryPerson.let { DeliveryPersonDto.fromDomain(it) },
                rating = order.rating
            )
        }
    }
}

data class OrderItemDto(
    val id: String,
    val name: String,
    val quantity: Int,
    val price: Double,
    val specialInstructions: String?
) {
    fun toDomain(): OrderItem {
        return OrderItem(
            id = id,
            name = name,
            quantity = quantity,
            price = price,
            specialInstructions = specialInstructions
        )
    }

    companion object {
        fun fromDomain(item: OrderItem): OrderItemDto {
            return OrderItemDto(
                id = item.id,
                name = item.name,
                quantity = item.quantity,
                price = item.price,
                specialInstructions = item.specialInstructions
            )
        }
    }
}

data class DeliveryPersonDto(
    val id: String,
    val name: String,
    val phone: String,
    val currentLocation: LocationDto?,
    val estimatedArrivalTime: Long?
) {
    fun toDomain(): DeliveryPerson {
        return DeliveryPerson(
            id = id,
            name = name,
            phone = phone,
            currentLocation = currentLocation?.toDomain(),
            estimatedArrivalTime = estimatedArrivalTime
        )
    }

    companion object {
        fun fromDomain(deliveryPerson: DeliveryPerson): DeliveryPersonDto {
            return DeliveryPersonDto(
                id = deliveryPerson.id,
                name = deliveryPerson.name,
                phone = deliveryPerson.phone,
                currentLocation = deliveryPerson.currentLocation?.let { LocationDto.fromDomain(it) },
                estimatedArrivalTime = deliveryPerson.estimatedArrivalTime
            )
        }
    }
}