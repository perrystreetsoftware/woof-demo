package com.perrystreet.woof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import com.perrystreet.woof.designsystem.theme.Theme
import com.perrystreet.woof.designsystem.theme.WoofTheme
import com.perrystreet.woof.presentation.navigation.WoofNavDisplay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val theme =
                when (isSystemInDarkTheme()) {
                    true -> WoofTheme.dark()
                    false -> WoofTheme.light()
                }
            Theme(theme = theme) {
                WoofNavDisplay()
            }
        }
    }
}
