package com.finderbar.hilsa.feature.pos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.finderbar.hilsa.core.ui.components.AppTextField
import com.finderbar.hilsa.core.ui.layout.AppScreen
import com.finderbar.hilsa.core.ui.theme.AppDesignSystem

@Composable
fun PosScreen() {
    val products = List(20) { "Product $it" }

    AppScreen(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppDesignSystem.spacing.medium),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("POS Terminal", style = MaterialTheme.typography.headlineSmall)
                BadgedBox(badge = { Badge { Text("5") } }) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
                }
            }
        }
    ) { padding ->
        Row(modifier = Modifier.padding(padding).fillMaxSize()) {
            // Product Selection Area
            Column(modifier = Modifier.weight(2f).padding(AppDesignSystem.spacing.medium)) {
                AppTextField(
                    value = "",
                    onValueChange = {},
                    label = "Search products...",
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
                )

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(120.dp),
                    modifier = Modifier.padding(top = AppDesignSystem.spacing.medium)
                ) {
                    items(products) { product ->
                        Card(
                            modifier = Modifier
                                .padding(AppDesignSystem.spacing.extraSmall)
                                .fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(AppDesignSystem.spacing.medium)) {
                                Text(product, style = MaterialTheme.typography.bodyMedium)
                                Text("$10.00", style = MaterialTheme.typography.labelSmall)
                            }
                        }
                    }
                }
            }

            // Simple Cart Sidebar Preview (for large screens)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(AppDesignSystem.spacing.medium)
            ) {
                Text("Current Order", style = MaterialTheme.typography.titleMedium)
                // Add cart items list here
            }
        }
    }
}
