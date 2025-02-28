package com.sm.poke_tv_features.detail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    pokemonId: String,
    viewModel: DetailScreenViewModel = koinViewModel<DetailScreenViewModel>(),
    onBackRequested: () -> Unit,
) {
    val viewState = viewModel.viewState.collectAsState()

    when (viewState.value) {
        is DetailScreenViewState.Initial -> {
            viewModel.getPokemonDetail(pokemonId)
        }
        else -> {
            DetailScreenContent(
                form = viewState.value.form,
                modifier = modifier
            )
        }
    }
}

@Composable
fun DetailScreenContent(
    form: DetailScreenViewForm,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = form.imageUrl,
            contentDescription = "${form.name} detail image",
            modifier = Modifier
                .width(300.dp)
                .height(300.dp),
            contentScale = ContentScale.Inside
        )
        Spacer(modifier = Modifier.width(32.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = form.name,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Weight: ${form.weight}",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Height: ${form.height}",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}