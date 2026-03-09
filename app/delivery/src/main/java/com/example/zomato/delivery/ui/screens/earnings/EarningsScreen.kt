package com.example.zomato.delivery.ui.screens.earnings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EarningsScreen(
    onBackClick: () -> Unit,
    viewModel: EarningsViewModel = hiltViewModel()
) {
    val earnings by viewModel.earnings.collectAsState()
    val totalEarnings by viewModel.totalEarnings.collectAsState()
    val totalDeliveries by viewModel.totalDeliveries.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Earnings") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Total Earnings",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "₹$totalEarnings",
                            style = MaterialTheme.typography.headlineLarge
                        )
                        Text(
                            text = "$totalDeliveries deliveries completed",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            item {
                Text(
                    text = "Recent Earnings",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            items(earnings) { earning ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Order #${earning.orderId}",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = earning.date,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Text(
                            text = "₹${earning.amount}",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            }
        }
    }
} 