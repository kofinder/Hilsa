package com.finderbar.hilsa.core.ui.preview

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.finderbar.hilsa.core.ui.theme.AppTheme
import com.finderbar.hilsa.core.ui.theme.HilsaTheme

@Composable
fun PreviewTheme(
    themeType: AppTheme = AppTheme.BLUE,
    content: @Composable () -> Unit
) {
    HilsaTheme(themeType = themeType) {
        Surface {
            content()
        }
    }
}
