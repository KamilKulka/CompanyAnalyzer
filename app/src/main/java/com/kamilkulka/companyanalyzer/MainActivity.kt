package com.kamilkulka.companyanalyzer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kamilkulka.companyanalyzer.screens.ClientsScreen
import com.kamilkulka.companyanalyzer.ui.theme.CompanyAnalyzerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompanyAnalyzerTheme {
                ClientsScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CompanyAnalyzerTheme {
        ClientsScreen()
    }
}