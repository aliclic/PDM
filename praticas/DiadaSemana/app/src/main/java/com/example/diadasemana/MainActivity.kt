package com.example.diadasemana

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diadasemana.Model.Calendario
import com.example.diadasemana.ui.theme.DiaDaSemanaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiaDaSemanaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiaDaSemana()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DiaDaSemanaTheme {
        Greeting("Android")
    }
}

@Composable
fun DiaDaSemana(modifier: Modifier = Modifier) {
    val hoje = Calendario().diaDaSemana()
    Row {
        Text(
            color = Color.Blue,
            text = "Dia: $hoje",
            modifier = modifier
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            color = Color.Blue,
            text = Calendario().diaDaSemanaPorData(
                24,3,2024)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DiaDaSemanaPreview() {
    DiaDaSemanaTheme {
        DiaDaSemana()
    }
}