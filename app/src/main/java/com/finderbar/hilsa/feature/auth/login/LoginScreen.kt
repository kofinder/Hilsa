package com.finderbar.hilsa.feature.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.finderbar.hilsa.R
import com.finderbar.hilsa.core.ui.components.AppButton
import com.finderbar.hilsa.core.ui.components.AppTextField
import com.finderbar.hilsa.core.ui.layout.AppScreen
import com.finderbar.hilsa.core.ui.theme.AppDesignSystem

/**
 * Enterprise Login Screen.
 */
@Composable
fun LoginScreen(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    onLoginSuccess: () -> Unit
) {
    LaunchedEffect(state.isLoggedIn) {
        if (state.isLoggedIn) {
            onLoginSuccess()
        }
    }

    AppScreen(
        isLoading = state.isLoading
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Background Illustration at the bottom
            Image(
                painter = painterResource(id = R.drawable.bg_login_bottom),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .align(Alignment.BottomCenter),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = AppDesignSystem.spacing.large)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(60.dp))

                Image(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "Hilsa Logo",
                    modifier = Modifier.size(160.dp)
                )

                Spacer(modifier = Modifier.height(AppDesignSystem.spacing.medium))

                Text(
                    text = "Welcome Back 👋",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                )

                Text(
                    text = "Please sign in to continue",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                )

                Spacer(modifier = Modifier.height(AppDesignSystem.spacing.extraLarge))

                AppTextField(
                    value = state.username,
                    onValueChange = { onEvent(LoginEvent.UsernameChanged(it)) },
                    label = "Employee ID / Username",
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    error = state.usernameError ?: state.errorMessage?.takeIf { state.username.isBlank() }
                )

                Spacer(modifier = Modifier.height(AppDesignSystem.spacing.medium))

                AppTextField(
                    value = state.password,
                    onValueChange = { onEvent(LoginEvent.PasswordChanged(it)) },
                    label = "Password",
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
                    visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { onEvent(LoginEvent.TogglePasswordVisibility) }) {
                            Icon(
                                imageVector = if (state.isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                contentDescription = "Toggle Password"
                            )
                        }
                    },
                    error = state.passwordError
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = state.rememberMe,
                            onCheckedChange = { onEvent(LoginEvent.ToggleRememberMe) },
                            colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary)
                        )
                        Text(text = "Remember me", style = MaterialTheme.typography.bodySmall)
                    }

                    TextButton(onClick = { /* Handle Forgot Password */ }) {
                        Text(
                            text = "Forgot Password?",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(AppDesignSystem.spacing.medium))

                AppButton(
                    text = "Sign In",
                    isLoading = state.isLoading,
                    onClick = { onEvent(LoginEvent.LoginClicked) },
                    leadingIcon = {
                        Surface(
                            modifier = Modifier.size(24.dp),
                            shape = androidx.compose.foundation.shape.CircleShape,
                            color = Color.White.copy(alpha = 0.2f)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = null,
                                modifier = Modifier.padding(4.dp).size(16.dp)
                            )
                        }
                    },
                    trailingIcon = { Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null) }
                )

                Spacer(modifier = Modifier.height(AppDesignSystem.spacing.medium))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HorizontalDivider(modifier = Modifier.weight(1f))
                    Text(
                        text = "OR",
                        modifier = Modifier.padding(horizontal = AppDesignSystem.spacing.medium),
                        style = MaterialTheme.typography.labelMedium.copy(color = Color.Gray)
                    )
                    HorizontalDivider(modifier = Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.height(AppDesignSystem.spacing.medium))

                OutlinedButton(
                    onClick = { /* Login with PIN */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(AppDesignSystem.dimensions.minButtonHeight),
                    shape = MaterialTheme.shapes.medium,
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray)
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_camera), // Placeholder for QR icon
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Login with PIN", color = Color.Black)
                }

                Spacer(modifier = Modifier.height(100.dp))

                Text(
                    text = "v1.0.0",
                    style = MaterialTheme.typography.labelMedium.copy(color = Color.Gray),
                    modifier = Modifier.padding(bottom = AppDesignSystem.spacing.medium)
                )
            }
        }
    }
}
