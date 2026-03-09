package com.example.zomato.customer.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ProfileScreen(
    onNavigateBack: () -> Unit,
    onEditProfile: () -> Unit,
    onAddresses: () -> Unit,
    onPaymentMethods: () -> Unit,
    onSettings: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val user by viewModel.user.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavigateBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = "Profile",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = onEditProfile) {
                Icon(Icons.Default.Edit, contentDescription = "Edit Profile")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // User Info
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = user?.name ?: "User Name",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = user?.email ?: "user@example.com",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = user?.phoneNumber ?: "+91 1234567890",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Menu Items
        ProfileMenuItem(
            icon = Icons.Default.LocationOn,
            title = "Addresses",
            onClick = onAddresses
        )
        ProfileMenuItem(
            icon = Icons.Default.Info,
            title = "Payment Methods",
            onClick = onPaymentMethods
        )
        ProfileMenuItem(
            icon = Icons.Default.Settings,
            title = "Settings",
            onClick = onSettings
        )
        ProfileMenuItem(
            icon = Icons.Default.ExitToApp,
            title = "Logout",
            onClick = { /* TODO: Handle logout */ }
        )
    }
}

@Composable
private fun ProfileMenuItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
} 