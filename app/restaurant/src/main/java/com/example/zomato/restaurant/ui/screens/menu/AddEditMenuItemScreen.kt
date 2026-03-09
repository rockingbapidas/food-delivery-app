package com.example.zomato.restaurant.ui.screens.menu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.zomato.domain.model.MenuItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditMenuItemScreen(
    menuItem: MenuItem? = null,
    onBackClick: () -> Unit,
    onSaveClick: (MenuItem) -> Unit,
    viewModel: AddEditMenuItemViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf(menuItem?.name ?: "") }
    var description by remember { mutableStateOf(menuItem?.description ?: "") }
    var price by remember { mutableStateOf(menuItem?.price?.toString() ?: "") }
    var category by remember { mutableStateOf(menuItem?.category ?: "") }
    var isVegetarian by remember { mutableStateOf(menuItem?.isVegetarian ?: false) }
    var isSpicy by remember { mutableStateOf(menuItem?.isSpicy ?: false) }

    val categories by viewModel.categories.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (menuItem == null) "Add Menu Item" else "Edit Menu Item") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            val priceValue = price.toDoubleOrNull() ?: 0.0
                            val newMenuItem = MenuItem(
                                id = menuItem?.id ?: "",
                                name = name,
                                description = description,
                                price = priceValue,
                                category = category,
                                isVegetarian = isVegetarian,
                                isSpicy = isSpicy,
                                imageUrl = menuItem?.imageUrl ?: "",
                                restaurantId = "",
                                isAvailable = true
                            )
                            onSaveClick(newMenuItem)
                        },
                        enabled = name.isNotBlank() && price.isNotBlank() && category.isNotBlank()
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Save")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Price") },
                modifier = Modifier.fillMaxWidth(),
                prefix = { Text("₹") }
            )

            ExposedDropdownMenuBox(
                expanded = false,
                onExpandedChange = { }
            ) {
                OutlinedTextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text("Category") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    readOnly = true
                )

                ExposedDropdownMenu(
                    expanded = false,
                    onDismissRequest = { }
                ) {
                    categories.forEach { cat ->
                        DropdownMenuItem(
                            text = { Text(cat) },
                            onClick = { category = cat }
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Checkbox(
                        checked = isVegetarian,
                        onCheckedChange = { isVegetarian = it }
                    )
                    Text("Vegetarian")
                }

                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Checkbox(
                        checked = isSpicy,
                        onCheckedChange = { isSpicy = it }
                    )
                    Text("Spicy")
                }
            }
        }
    }
} 