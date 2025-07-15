package com.avallon.cantina.navigation // Certifique-se que o pacote está correto

object AppDestinations {
    const val LOGIN_ROUTE = "login" // Você pode manter ou remover se não estiver usando ainda
    const val DASHBOARD_ROUTE = "dashboard"
    const val STOCK_ROUTE = "stock" // ADICIONE ESTA LINHA
    // Em AppDestinations
    const val ADD_PRODUCT_ROUTE = "add_product"
    const val SUPPLIERS_ROUTE = "suppliers" // <-- ADICIONE OU VERIFIQUE ESTA LINHA

    // Adicione outras rotas aqui no futuro (ex: produtos, vendas, etc.)
    // const val STOCK_LOG_ROUTE = "stock_log"
    // const val PRODUCT_DETAILS_ROUTE = "product_details"
    // const val PRODUCT_ID_ARG = "productId"
    // const val PRODUCT_DETAILS_WITH_ARG_ROUTE = "$PRODUCT_DETAILS_ROUTE/{$PRODUCT_ID_ARG}"
}

