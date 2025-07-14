package com.avallon.cantina.ui.components // PACOTE CORRETO

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.avallon.cantina.ui.theme.CantinaTheme

@Composable
fun ProductCard( // NOME DA FUNÇÃO ATUALIZADO
    modifier: Modifier = Modifier,
    name: String, // nome em inglês
    quantity: Int, // quantidade em inglês
    imagePlaceholder: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = ColorPainter(imagePlaceholder),
                contentDescription = "Image of $name", // descrição em inglês
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
                    .background(
                        Color.Black.copy(alpha = 0.5f),
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "$quantity",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.85f))
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() { // NOME DA PREVIEW ATUALIZADO
    CantinaTheme {
        Row(modifier = Modifier.padding(8.dp)) {
            ProductCard( // USANDO ProductCard
                name = "Super Cool Product With a Long Name", // texto em inglês
                quantity = 10,
                imagePlaceholder = Color.Blue,
                onClick = {}
            )
            Spacer(modifier = Modifier.width(8.dp))
            ProductCard( // USANDO ProductCard
                name = "Item B",
                quantity = 5,
                imagePlaceholder = Color.Green,
                onClick = {}
            )
        }
    }
}

