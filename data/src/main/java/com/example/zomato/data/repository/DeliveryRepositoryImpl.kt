package com.example.zomato.data.repository

import com.example.zomato.domain.model.Delivery
import com.example.zomato.domain.model.DeliveryStatus
import com.example.zomato.domain.repository.DeliveryRepository
import com.example.zomato.data.api.DeliveryApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeliveryRepositoryImpl @Inject constructor(
    private val api: DeliveryApi
) : DeliveryRepository {

    override fun getCurrentDelivery(): Flow<Delivery?> = flow {
        try {
            val delivery = api.getCurrentDelivery().toDomain()
            emit(delivery)
        } catch (e: Exception) {
            emit(null)
        }
    }

    override suspend fun getDeliveries(): Result<List<Delivery>> = runCatching {
        api.getDeliveries().map { it.toDomain() }
    }

    override suspend fun getDeliveryById(id: String): Result<Delivery> = runCatching {
        api.getDeliveryById(id).toDomain()
    }

    override suspend fun updateDeliveryStatus(deliveryId: String, status: DeliveryStatus): Result<Unit> = runCatching {
        api.updateDeliveryStatus(deliveryId, status.name)
    }

    override suspend fun contactDeliveryPerson(deliveryId: String): Result<Unit> = runCatching {
        api.contactDeliveryPerson(deliveryId)
    }

    override suspend fun rateDelivery(deliveryId: String, rating: Int, feedback: String?): Result<Unit> = runCatching {
        api.rateDelivery(deliveryId, rating, feedback)
    }
} 