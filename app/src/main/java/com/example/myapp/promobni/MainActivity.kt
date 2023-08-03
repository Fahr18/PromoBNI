package com.example.myapp.promobni

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.myapp.promobni.model.Promo
import com.example.myapp.promobni.ui.theme.RecyclerViewJCYTTTheme
import com.example.myapp.promobni.util.JsonUtil
import androidx.compose.foundation.Image
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecyclerViewJCYTTTheme {
                val jsonString = JsonUtil.readJsonFromAssets("promo.json", LocalContext.current)
                val promoItems = JsonUtil.fromJson(jsonString)
                RecyclerView(promoItems)
            }
        }
    }
}

@Composable
fun PromoImage(promoItem: Promo) {
    Surface(color = MaterialTheme.colors.secondary, modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
        Column(modifier = Modifier.padding(24.dp).fillMaxWidth()) {
            Text(text = promoItem.desc, color = Color.Black)
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = rememberImagePainter(data = promoItem.url!!),
                contentDescription = "Promo Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop,
            )
        }
    }
}



@Composable
fun RecyclerView(promoItems: List<Promo> = emptyList()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Promo BNI") },
                backgroundColor = Color(android.graphics.Color.parseColor("#50FAF4")),
                contentColor = Color.Black
            )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
            items(items = promoItems) { promoItem ->
                PromoImage(promoItem)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RecyclerViewJCYTTTheme {
        RecyclerView()
    }
}