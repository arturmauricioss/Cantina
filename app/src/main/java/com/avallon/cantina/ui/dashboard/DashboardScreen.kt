@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.avallon.cantina.ui.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api // Já estava no @file:OptIn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.avallon.cantina.ui.theme.CantinaTheme // Certifique-se que este é o caminho correto para seu tema

@Composable
fun DashboardScreen(
    onNavigateToStock: () -> Unit // Parâmetro para o callback de navegação
    // Se você tiver um botão "Clientes" que também navega, adicionaria:
    // onNavigateToClientes: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard Cantina") }, // Ou apenas "Dashboard"
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Abrir drawer/menu lateral */ }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: Abrir tela de alertas/notificações */ }) {
                        Icon(Icons.Filled.Notifications, contentDescription = "Alertas")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp), // Padding geral para o conteúdo da coluna
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp) // Espaço entre os elementos filhos da Coluna
        ) {
            Text(
                "Bem-vindo ao Dashboard!", // Ou "Welcome to Dashboard!"
                style = MaterialTheme.typography.headlineSmall
            )

            // Seus cards de saldo
            DashboardCard("Saldo do Dia", "R$ 0,00") // Ou "Daily Balance"
            DashboardCard("Saldo da Semana", "R$ 0,00") // Ou "Weekly Balance"
            DashboardCard("Saldo do Mês", "R$ 0,00") // Ou "Monthly Balance"

            Text(
                "Gráfico de Estoque/Capital (Placeholder)", // Ou "Stock/Capital Chart (Placeholder)"
                style = MaterialTheme.typography.titleMedium
            )
            Box( // Placeholder para o gráfico
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    // .background(MaterialTheme.colorScheme.surfaceVariant) // Opcional: cor de fundo para o placeholder
                    .padding(8.dp), // Padding dentro da Box do gráfico
                contentAlignment = Alignment.Center
            ) {
                Text("Área do Gráfico") // Ou "Chart Area"
            }

            // Seção de Botões de Ação Rápida
            Text(
                "Ações Rápidas", // Ou "Quick Actions"
                style = MaterialTheme.typography.titleMedium
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = onNavigateToStock, // Ação do botão chama o lambda
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Estoque") // Ou "Stock"
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = {
                        // Se você adicionar onNavigateToClientes como parâmetro:
                        // onNavigateToClientes()
                        // Por enquanto:
                        /* TODO: Navegar para a tela de Clientes */
                        println("Botão Clientes clicado - Navegação não implementada")
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Clientes") // Ou "Customers"
                }
            }
        }
    }
}

@Composable
fun DashboardCard(title: String, data: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp) // Padding dentro de cada card
        ) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = data, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    CantinaTheme {
        // Para a preview, passamos um lambda vazio para onNavigateToStock
        // porque a preview não executa a navegação real.
        DashboardScreen(onNavigateToStock = {})
    }
}
