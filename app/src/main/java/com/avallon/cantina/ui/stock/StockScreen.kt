@file:OptIn(ExperimentalMaterial3Api::class) // Para componentes do Material 3

package com.avallon.cantina.ui.stock // PACOTE CORRETO

// import androidx.compose.material.icons.filled.Group // Exemplo de ícone para Fornecedores, se quiser
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.avallon.cantina.ui.components.ProductCard
import com.avallon.cantina.ui.theme.CantinaTheme
import kotlinx.coroutines.delay
import java.text.NumberFormat
import java.util.Locale

data class ProductStockItem(
    val id: Int,
    val name: String,
    val quantity: Int,
    val imagePlaceholder: Color,
    val costPrice: Double,
    val salePrice: Double
)

val sampleProducts = listOf(
    ProductStockItem(1, "Refrigerante", 20, Color.Red, 2.50, 4.00),
    ProductStockItem(2, "Biscoito", 15, Color.Yellow, 1.50, 3.00),
    ProductStockItem(3, "Chocolate", 30, Color.Magenta.copy(alpha = 0.5f), 3.00, 5.00),
    ProductStockItem(4, "Bombom", 50, Color.Cyan, 1.00, 2.00),
    ProductStockItem(5, "Bolo", 10, Color.Green, 3.00, 5.00),
)


@Composable
fun StockScreen(
    onNavigateBack: () -> Unit,
    onNavigateToLog: () -> Unit,       // Para o ícone de histórico na TopAppBar
    onProductClick: (productId: Int) -> Unit,
    onNavigateToAddProduct: () -> Unit, // Para o ícone "+" na TopAppBar
    onNavigateToSuppliers: () -> Unit  // Para o botão "Fornecedores"
    // viewModel: StockViewModel ...
) {
    val stockValueCostTotal = sampleProducts.sumOf { it.quantity * it.costPrice }
    val stockValueSaleTotal = sampleProducts.sumOf { it.quantity * it.salePrice }

    var showCostValue by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(5000) // Alterna a cada 5 segundos
            showCostValue = !showCostValue
        }
    }

    val currencyFormatter = remember { NumberFormat.getCurrencyInstance(Locale("pt", "BR")) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Estoque") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                },
                actions = {
                    // Ícone para Histórico de Estoque
                    IconButton(onClick = onNavigateToLog) {
                        Icon(
                            Icons.AutoMirrored.Filled.ReceiptLong, // Ícone para log/histórico
                            contentDescription = "Histórico de Estoque"
                        )
                    }
                    // Ícone para Adicionar Produto
                    IconButton(onClick = onNavigateToAddProduct) {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = "Adicionar Produto"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Card do Banner de Valor Total
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    val bannerText = if (showCostValue) {
                        "Valor de Custo Total: ${currencyFormatter.format(stockValueCostTotal)}"
                    } else {
                        "Valor de Venda Total: ${currencyFormatter.format(stockValueSaleTotal)}"
                    }
                    Text(bannerText, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }

            // Botão Fornecedores
            Button(
                onClick = onNavigateToSuppliers, // Usa o novo callback
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                // Se quiser um ícone para o botão Fornecedores:
                // Icon(Icons.Filled.Group, contentDescription = "Fornecedores", modifier = Modifier.padding(end = 8.dp))
                Text("Fornecedores")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Produtos", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(8.dp))

            // LazyVerticalGrid para os produtos
            if (sampleProducts.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Sem produtos no estoque.")
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(sampleProducts.size) { index ->
                        val product = sampleProducts[index]
                        ProductCard(
                            name = product.name,
                            quantity = product.quantity,
                            imagePlaceholder = product.imagePlaceholder,
                            onClick = { onProductClick(product.id) }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 380, heightDp = 720)
@Composable
fun StockScreenPreview() {
    CantinaTheme {
        StockScreen(
            onNavigateBack = {},
            onNavigateToLog = {},
            onProductClick = {},
            onNavigateToAddProduct = {},
            onNavigateToSuppliers = {} // Adicionar o novo callback aqui também
        )
    }
}
