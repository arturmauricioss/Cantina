package com.avallon.cantina // Use o seu namespace correto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box // Para o placeholder da AddProductScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text // Para o placeholder da AddProductScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment // Para o placeholder da AddProductScreen
import androidx.compose.ui.Modifier
import com.avallon.cantina.ui.theme.CantinaTheme
import com.avallon.cantina.ui.login.LoginScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.avallon.cantina.ui.dashboard.DashboardScreen
import com.avallon.cantina.ui.stock.StockScreen
// IMPORTAR AppDestinations do seu arquivo
import com.avallon.cantina.AppDestinations
// IMPORTAR SUA FUTURA AddProductScreen (ou comente se ainda não criou o arquivo)
// import com.avallon.cantina.ui.add_product.AddProductScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CantinaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigator()
                }
            }
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppDestinations.LOGIN_ROUTE
    ) {
        composable(route = AppDestinations.LOGIN_ROUTE) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(AppDestinations.DASHBOARD_ROUTE) {
                        popUpTo(AppDestinations.LOGIN_ROUTE) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(route = AppDestinations.DASHBOARD_ROUTE) {
            DashboardScreen(
                onNavigateToStock = {
                    navController.navigate(AppDestinations.STOCK_ROUTE)
                }
                // Missing onNavigateToSuppliers here
            )
        }

        composable(route = AppDestinations.STOCK_ROUTE) {
            StockScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToLog = {
                    println("Navegar para Log do Estoque clicado")
                    // Futuramente: navController.navigate(AppDestinations.STOCK_LOG_ROUTE)
                },
                onProductClick = { productId ->
                    println("Produto $productId clicado")
                    // Futuramente: navController.navigate("${AppDestinations.PRODUCT_DETAILS_ROUTE}/$productId")
                },
                onNavigateToAddProduct = {
                    navController.navigate(AppDestinations.ADD_PRODUCT_ROUTE)
                },
                onNavigateToSuppliers = { // <<--- ADICIONE ESTA LINHA E O LAMBDA
                    // Defina o que acontece quando onNavigateToSuppliers é acionado.
                    // Por exemplo, navegar para uma nova tela de Fornecedores:
                    navController.navigate(AppDestinations.SUPPLIERS_ROUTE)
                    // Ou, se você ainda não tem a tela, pode apenas registrar no console:
                    // println("Navegar para Fornecedores clicado")
                }
            )
        }

        // NOVA ROTA PARA ADICIONAR PRODUTO

        composable(route = AppDestinations.SUPPLIERS_ROUTE) {
            // Substitua pelo seu Composable SuppliersScreen real
            // Exemplo:
            // SuppliersScreen(
            //     onNavigateBack = { navController.popBackStack() }
            // )

            // Placeholder enquanto sua SuppliersScreen não está pronta:
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Tela de Fornecedores (Em Desenvolvimento)")
            }
        }

        // Adicione outros composables para outras rotas aqui
        // Por exemplo, para um futuro histórico de estoque:
        /*
        composable(route = AppDestinations.STOCK_LOG_ROUTE) {
            // StockLogScreen(onNavigateBack = { navController.popBackStack() })
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Tela de Histórico de Estoque (Em Desenvolvimento)")
            }
        }
        */
    }
}