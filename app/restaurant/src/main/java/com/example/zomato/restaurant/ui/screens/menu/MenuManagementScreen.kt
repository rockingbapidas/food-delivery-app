package com.example.zomato.restaurant.ui.screens.menu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.zomato.domain.model.MenuItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import kotlinx.coroutines.launch
import com.example.zomato.restaurant.ui.screens.menu.AddEditMenuItemScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuManagementScreen(
    onBackClick: () -> Unit,
    onAddItemClick: () -> Unit,
    onEditItemClick: (MenuItem) -> Unit,
    onNavigateToOrders: () -> Unit,
    onNavigateToAnalytics: () -> Unit,
    viewModel: MenuManagementViewModel = hiltViewModel()
) {
    val menuItems by viewModel.menuItems.collectAsState()
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    val categories by viewModel.categories.collectAsState()

    // Bottom sheet state
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    var showSheet by remember { mutableStateOf(false) }
    var editingMenuItem by remember { mutableStateOf<MenuItem?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }

    // Sheet open/close handlers
    fun openSheet(item: MenuItem?) {
        editingMenuItem = item
        showSheet = true
    }
    fun closeSheet() {
        showSheet = false
        editingMenuItem = null
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Menu Management") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { openSheet(null) }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Item")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Category filter
            ScrollableTabRow(
                selectedTabIndex = categories.indexOf(selectedCategory).coerceAtLeast(0),
                modifier = Modifier.fillMaxWidth()
            ) {
                Tab(
                    selected = selectedCategory == null,
                    onClick = { selectedCategory = null },
                    text = { Text("All") }
                )
                categories.forEach { category ->
                    Tab(
                        selected = selectedCategory == category,
                        onClick = { selectedCategory = category },
                        text = { Text(category) }
                    )
                }
            }

            // Menu items list
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = menuItems.filter { selectedCategory == null || it.category == selectedCategory }
                ) { item ->
                    MenuItemCard(
                        item = item,
                        onEditClick = { openSheet(item) },
                        onDeleteClick = {
                            coroutineScope.launch {
                                viewModel.deleteMenuItem(item.id)
                                snackbarHostState.showSnackbar("Menu item deleted")
                            }
                        }
                    )
                }
            }
        }
    }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { closeSheet() },
            sheetState = sheetState
        ) {
            AddEditMenuItemScreen(
                menuItem = editingMenuItem,
                onBackClick = { closeSheet() },
                onSaveClick = { menuItem ->
                    coroutineScope.launch {
                        if (editingMenuItem == null) {
                            viewModel.addMenuItem(menuItem)
                            snackbarHostState.showSnackbar("Menu item added")
                        } else {
                            viewModel.updateMenuItem(menuItem)
                            snackbarHostState.showSnackbar("Menu item updated")
                        }
                        closeSheet()
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MenuItemCard(
    item: MenuItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "₹${item.price}",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = item.category,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Row {
                IconButton(onClick = onEditClick) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit item"
                    )
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete item"
                    )
                }
            }
        }
    }
} 