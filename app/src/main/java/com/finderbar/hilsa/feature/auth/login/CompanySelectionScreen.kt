package com.finderbar.hilsa.feature.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Search
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
fun CompanySelectionScreen(
    onBack: () -> Unit,
    onCompanySelected: (String) -> Unit
) {
    var selectedCompany by remember { mutableStateOf<String?>(null) }
    val companies = listOf(
        "Hilsa Head Office" to "Sittwe, Rakhine State",
        "Hilsa Kyaukphyu Office" to "Kyaukphyu, Rakhine State",
        "Hilsa Maungdaw Office" to "Maungdaw, Rakhine State",
        "Hilsa Buthidaung Office" to "Buthidaung, Rakhine State",
        "Hilsa Pauktaw Office" to "Pauktaw, Rakhine State"
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
                onClick = { selectedCompany?.let { onCompanySelected(it) } },
                enabled = selectedCompany != null,
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
                    text = "Select Office",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "Choose your office to continue",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
            }

            AppTextField(
                value = "",
                onValueChange = {},
                label = "Search office...",
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                modifier = Modifier.padding(vertical = AppDesignSystem.spacing.medium)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(AppDesignSystem.spacing.small),
                modifier = Modifier.fillMaxSize()
            ) {
                items(companies) { (name, address) ->
                    SelectionItem(
                        title = name,
                        subtitle = address,
                        icon = Icons.Default.Business,
                        isSelected = selectedCompany == name,
                        onClick = { selectedCompany = name }
                    )
                }
            }
        }
    }
}
