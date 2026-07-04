package com.finderbar.hilsa.feature.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.finderbar.hilsa.R
import com.finderbar.hilsa.core.ui.components.AppButton
import com.finderbar.hilsa.core.ui.components.AppTextField
import com.finderbar.hilsa.core.ui.components.SelectionItem
import com.finderbar.hilsa.core.ui.layout.AppScreen
import com.finderbar.hilsa.core.ui.theme.AppDesignSystem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PositionSelectionScreen(
    onBack: () -> Unit,
    departmentName: String,
    onPositionSelected: (String) -> Unit
) {
    var selectedPosition by remember { mutableStateOf<String?>(null) }
    var tabIndex by remember { mutableIntStateOf(0) }
    val positions = listOf(
        "Cashier" to "12 Employees",
        "Sales Executive" to "8 Employees",
        "Store Keeper" to "4 Employees",
        "Supervisor" to "4 Employees",
        "Manager" to "3 Employees",
        "Admin" to "2 Employees"
    )

    AppScreen(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        bottomBar = {
            AppButton(
                text = "Continue",
                onClick = { selectedPosition?.let { onPositionSelected(it) } },
                enabled = selectedPosition != null,
                modifier = Modifier
                    .padding(AppDesignSystem.spacing.medium)
                    .navigationBarsPadding(),
                trailingIcon = { Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null) }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = AppDesignSystem.spacing.medium)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.height(AppDesignSystem.spacing.medium))
                Text(
                    text = "Select Position / Employee",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "Choose your position or employee",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
            }

            AppTextField(
                value = "",
                onValueChange = {},
                label = "Search position or employee...",
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                modifier = Modifier.padding(vertical = AppDesignSystem.spacing.medium)
            )

            TabRow(
                selectedTabIndex = tabIndex,
                modifier = Modifier.padding(bottom = AppDesignSystem.spacing.medium),
                containerColor = MaterialTheme.colorScheme.surface,
                divider = {}
            ) {
                Tab(selected = tabIndex == 0, onClick = { tabIndex = 0 }) {
                    Text("By Position", modifier = Modifier.padding(vertical = 12.dp))
                }
                Tab(selected = tabIndex == 1, onClick = { tabIndex = 1 }) {
                    Text("By Employee", modifier = Modifier.padding(vertical = 12.dp))
                }
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(AppDesignSystem.spacing.small),
                modifier = Modifier.fillMaxSize()
            ) {
                items(positions) { (name, count) ->
                    SelectionItem(
                        title = name,
                        subtitle = count,
                        icon = Icons.Default.Groups,
                        isSelected = selectedPosition == name,
                        onClick = { selectedPosition = name },
                        showArrow = true
                    )
                }
            }
        }
    }
}
