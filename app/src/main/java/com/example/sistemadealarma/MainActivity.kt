package com.example.sistemadealarma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.sistemadealarma.ui.theme.SistemadealarmaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SistemadealarmaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AlarmView(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AlarmView(modifier: Modifier = Modifier) {
    var currentLoad by rememberSaveable { mutableStateOf(0) }
    val overloadThreshold = 100

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Carga Actual: $currentLoad%")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (currentLoad < 150) {
                currentLoad += 20
            }
        }) {
            Text(text = "Aumentar Carga")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (currentLoad > overloadThreshold) {
            Text(
                text = "¡Alarma! Sobrecarga detectada.",
                color = androidx.compose.ui.graphics.Color.Red
            )
        } else {
            Text(text = "Estado Seguro", color = androidx.compose.ui.graphics.Color.Green)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAlarmView() {
    SistemadealarmaTheme {
        AlarmView()
    }
}



