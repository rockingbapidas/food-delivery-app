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
    val status: String,
    val orderTime: Long,
    val estimatedDeliveryTime: Long,
    val deliveryAddress: String,
    val customerName: String,
    val customerPhone: String,
    val specialInstructions: String?,
    val paymentMethod: String,
    val deliveryPerson: DeliveryPersonDto
) {
    fun toDomain(): Order {
        return Order(
            id = id,
            restaurantId = restaurantId,
            restaurantName = restaurantName,
            items = items.map { it.toDomain() },
            totalAmount = totalAmount,
            status = OrderStatus.valueOf(status),
            orderTime = orderTime,
            estimatedDeliveryTime = estimatedDeliveryTime,
            deliveryAddress = deliveryAddress,
            customerName = customerName,
            customerPhone = customerPhone,
            specialInstructions = specialInstructions,
            paymentMethod = PaymentMethod.valueOf(paymentMethod),
            deliveryPerson = deliveryPerson.toDomain()
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
                status = order.status.name,
                orderTime = order.orderTime,
                estimatedDeliveryTime = order.estimatedDeliveryTime,
                deliveryAddress = order.deliveryAddress,
                customerName = order.customerName,
                customerPhone = order.customerPhone,
                specialInstructions = order.specialInstructions,
                paymentMethod = order.paymentMethod.name,
                deliveryPerson = order.deliveryPerson.let { DeliveryPersonDto.fromDomain(it) }
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