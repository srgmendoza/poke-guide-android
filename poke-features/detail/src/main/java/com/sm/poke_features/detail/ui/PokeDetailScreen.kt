package com.sm.poke_features.detail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.sm.core.ui.components.PokeLoaderView
import com.sm.poke_domain.models.PokeDetailDomainModel
import com.sm.poke_domain.models.PokeTypeDomainModel

@Composable
fun PokemonDetailScreen(
    pokeName: String,
    onBackRequested: () -> Unit,
    viewModel: PokemonDetailScreenViewModel
) {

    val viewState = viewModel.viewState.collectAsState()

    when (viewState.value) {
        is PokemonDetailScreenViewState.Initial -> {
            viewModel.getPokemonDetail(pokeName)
        }

        is PokemonDetailScreenViewState.Loading -> {
            PokeLoaderView()
        }

        else -> {
            PokemonDetailScreenContent(
                viewForm = viewState.value.form,
                onGoBack = onBackRequested
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PokemonDetailScreenContent(
    viewForm: PokemonDetailScreenViewForm,
    onGoBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "${viewForm.name} details",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onGoBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Pokémon Image
            Card(
                modifier = Modifier
                    .size(200.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                AsyncImage(
                    model = viewForm.imageUrl,
                    //placeholder = painterResource(id = R.drawable.marvel_placeholder),
                    contentDescription = "${viewForm.name} image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    contentScale = ContentScale.Fit
                )
            }

            // Pokémon Name
            Text(
                text = viewForm.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )

            // Pokémon ID
            Text(
                text = viewForm.id,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Pokémon Height and Weight
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Height",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = viewForm.height,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 16.sp
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Weight",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = viewForm.weight,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPokemonDetailScreen() {
    PokemonDetailScreenContent(
        onGoBack = {},
        viewForm = PokemonDetailScreenViewForm(
            domainModel = PokeDetailDomainModel(
                id = "25",
                name = "Pikachu",
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
                height = 4,
                weight = 6,
                topType = PokeTypeDomainModel(null, null),
                soundUrl = ""
            )
        ),

        )
}
