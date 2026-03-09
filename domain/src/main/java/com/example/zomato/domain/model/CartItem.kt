package com.example.zomato.domain.model

data class CartItem(
    val menuItem: MenuItem,
    val quantity: Int,
    val specialInstructions: String? = null
) 