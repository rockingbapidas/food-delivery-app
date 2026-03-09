package com.example.zomato.domain.repository

import com.example.zomato.domain.model.Delivery
import com.example.zomato.domain.model.DeliveryStatus
import kotlinx.coroutines.flow.Flow

interface DeliveryRepository {
    fun getCurrentDelivery(): Flow<Delivery?>
    suspend fun getDeliveries(): Result<List<Delivery>>
    suspend fun getDeliveryById(id: String): Result<Delivery>
    suspend fun updateDeliveryStatus(deliveryId: String, status: DeliveryStatus): Result<Unit>
    suspend fun contactDeliveryPerson(deliveryId: String): Result<Unit>
    suspend fun rateDelivery(deliveryId: String, rating: Int, feedback: String?): Result<Unit>
} 