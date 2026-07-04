package com.finderbar.hilsa.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.finderbar.hilsa.core.ui.components.AppButton
import com.finderbar.hilsa.core.ui.layout.AppScreen
import com.finderbar.hilsa.core.ui.theme.AppDesignSystem

@Composable
fun HomeScreen(
    onLogout: () -> Unit
) {
    AppScreen(
        topBar = {
            Column(modifier = Modifier.padding(AppDesignSystem.spacing.medium)) {
                Text(text = "Dashboard", style = MaterialTheme.typography.headlineMedium)
                Text(text = "Hilsa Retail - Main Branch", style = MaterialTheme.typography.bodySmall)
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = AppDesignSystem.spacing.medium,
                end = AppDesignSystem.spacing.medium,
                top = padding.calculateTopPadding(),
                bottom = AppDesignSystem.spacing.medium
            ),
            verticalArrangement = Arrangement.spacedBy(AppDesignSystem.spacing.medium)
        ) {
            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    DashboardCard(modifier = Modifier.weight(1f), title = "Today's Sales", value = "$12,450.00")
                    DashboardCard(modifier = Modifier.weight(1f), title = "Orders", value = "124")
                }
            }
            
            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    DashboardCard(modifier = Modifier.weight(1f), title = "Revenue", value = "$8,200.00")
                    DashboardCard(modifier = Modifier.weight(1f), title = "Customers", value = "12")
                }
            }

            item {
                Text("Inventory Alerts", style = MaterialTheme.typography.titleMedium)
                Card(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                    Text(
                        text = "5 products are low in stock",
                        modifier = Modifier.padding(AppDesignSystem.spacing.medium),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            item {
                Spacer(Modifier.height(20.dp))
                AppButton(
                    onClick = onLogout,
                    text = "Logout",
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                )
            }
        }
    }
}

@Composable
fun DashboardCard(modifier: Modifier = Modifier, title: String, value: String) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(AppDesignSystem.spacing.medium)) {
            Text(text = title, style = MaterialTheme.typography.labelMedium)
            Text(text = value, style = MaterialTheme.typography.headlineSmall)
        }
    }
}
