package com.example.zomato.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.zomato.domain.model.MenuItem

@Composable
fun MenuItemCard(
    menuItem: MenuItem,
    onItemClick: (MenuItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick(menuItem) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                model = menuItem.imageUrl,
                contentDescription = menuItem.name,
                modifier = Modifier
                    .size(80.dp),
                contentScale = ContentScale.Crop
            )
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = menuItem.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = menuItem.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "₹${menuItem.price}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    if (menuItem.isVegetarian) {
                        Text(
                            text = "🟢 Veg",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    if (menuItem.isSpicy) {
                        Text(
                            text = "🌶️ Spicy",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
} 