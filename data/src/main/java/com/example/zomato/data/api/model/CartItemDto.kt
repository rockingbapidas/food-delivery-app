package com.example.zomato.data.api.model

import com.example.zomato.domain.model.CartItem

data class CartItemDto(
    val menuItem: MenuItemDto,
    val quantity: Int,
    val specialInstructions: String? = null
) {
    fun toDomain() = CartItem(
        menuItem = menuItem.toDomain(),
        quantity = quantity,
        specialInstructions = specialInstructions
    )

    companion object {
        fun fromDomain(cartItem: CartItem) = CartItemDto(
            menuItem = MenuItemDto.fromDomain(cartItem.menuItem),
            quantity = cartItem.quantity,
            specialInstructions = cartItem.specialInstructions
        )
    }
}