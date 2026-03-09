package com.example.zomato.customer.ui.screens.order

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.zomato.domain.model.OrderStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderTrackingScreen(
    orderId: String,
    onBackClick: () -> Unit,
    viewModel: OrderTrackingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(orderId) {
        viewModel.loadOrderDetails(orderId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Track Order") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        when (uiState) {
            is OrderTrackingUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is OrderTrackingUiState.Success -> {
                val order = (uiState as OrderTrackingUiState.Success).order
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Order #${order.id}",
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    OrderStatusTimeline(
                        currentStatus = order.status,
                        restaurantName = order.restaurantName,
                        deliveryAddress = order.deliveryAddress
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    DeliveryPersonInfo(
                        name = order.deliveryPerson.name,
                        phone = order.deliveryPerson.phone
                    )
                }
            }
            is OrderTrackingUiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = (uiState as OrderTrackingUiState.Error).message,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@Composable
private fun OrderStatusTimeline(
    currentStatus: OrderStatus,
    restaurantName: String,
    deliveryAddress: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        OrderStatusItem(
            icon = Icons.Default.ShoppingCart,
            title = "Order Confirmed",
            description = "Your order has been confirmed at $restaurantName",
            isCompleted = currentStatus >= OrderStatus.ACCEPTED
        )

        OrderStatusItem(
            icon = Icons.Default.Notifications,
            title = "Preparing Order",
            description = "The restaurant is preparing your order",
            isCompleted = currentStatus >= OrderStatus.PREPARING
        )

        OrderStatusItem(
            icon = Icons.Default.AccountBox,
            title = "On the Way",
            description = "Your order is on the way to $deliveryAddress",
            isCompleted = currentStatus >= OrderStatus.OUT_FOR_DELIVERY
        )

        OrderStatusItem(
            icon = Icons.Default.CheckCircle,
            title = "Delivered",
            description = "Your order has been delivered",
            isCompleted = currentStatus >= OrderStatus.DELIVERED
        )
    }
}

@Composable
private fun OrderStatusItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String,
    isCompleted: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = if (isCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun DeliveryPersonInfo(
    name: String,
    phone: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Delivery Person",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = phone,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
} 