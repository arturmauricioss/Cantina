package com.avallon.cantina.ui.login

import android.widget.Toast // Import para Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext // Import para LocalContext
import androidx.compose.ui.semantics.password
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.avallon.cantina.ui.theme.CantinaTheme
import kotlin.text.isBlank


@androidx.compose.runtime.Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit // Callback para notificar sobre o sucesso do login
) {
    var username by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf("") }
    var password by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf("") }
    val context = androidx.compose.ui.platform.LocalContext.current

    androidx.compose.foundation.layout.Column(
        // ... (o restante do Column permanece o mesmo) ...
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        androidx.compose.material3.Text(
            text = "Cantina App",
            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
            modifier = androidx.compose.ui.Modifier.padding(bottom = 24.dp)
        )

        androidx.compose.material3.OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { androidx.compose.material3.Text("Usuário ou Email") },
            singleLine = true,
            modifier = androidx.compose.ui.Modifier.fillMaxWidth()
        )

        androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))

        androidx.compose.material3.OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { androidx.compose.material3.Text("Senha") },
            visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation(),
            singleLine = true,
            modifier = androidx.compose.ui.Modifier.fillMaxWidth()
        )

        androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.height(24.dp))

        androidx.compose.material3.Button(
            onClick = {
                if (username.isBlank() || password.isBlank()) {
                    Toast.makeText(context, "Usuário e senha são obrigatórios!", Toast.LENGTH_LONG).show()
                } else if (username == "admin" && password == "admin") {
                    Toast.makeText(context, "Login bem-sucedido!", Toast.LENGTH_SHORT).show()
                    onLoginSuccess() // CHAMA O CALLBACK DE SUCESSO
                } else {
                    Toast.makeText(context, "Usuário ou senha inválidos!", Toast.LENGTH_LONG).show()
                }
            },
            modifier = androidx.compose.ui.Modifier.fillMaxWidth()
        ) {
            androidx.compose.material3.Text("Entrar")
        }
    }
}

    @androidx.compose.ui.tooling.preview.Preview(showBackground = true)
    @androidx.compose.runtime.Composable
    fun LoginScreenPreview() {
        CantinaTheme {
            LoginScreen(onLoginSuccess = {}) // Fornece uma implementação vazia para o preview
        }
    }


