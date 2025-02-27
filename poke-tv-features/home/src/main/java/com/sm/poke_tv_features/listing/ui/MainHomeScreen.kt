package com.sm.poke_tv_features.listing.ui

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Carousel
import androidx.tv.material3.CarouselDefaults
import androidx.tv.material3.CarouselState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import androidx.tv.material3.rememberCarouselState
import coil3.compose.AsyncImage
import com.sm.core.navigation.NavTVDestination
import com.sm.core.ui.components.PokeItemView
import com.sm.poke_domain.models.PokeTypesWithChildrenDomainModel
import com.sm.poke_domain.models.PokemonListItemDomainModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainHomeScreen(
    modifier: Modifier = Modifier,
    viewModel: MainHomeScreenViewModel = koinViewModel<MainHomeScreenViewModel>()
) {
    val viewState = viewModel.viewState.collectAsState()
    when (viewState.value) {
        is MainHomeScreenViewState.Initial -> {
            viewModel.fetchContent()
        }
        else -> {
            MainHomeScreenContent(
                pokemons = viewState.value.form.pokemons,
                ribbons = viewState.value.form.ribbons,
                modifier = Modifier
                    .fillMaxSize()
                    .then(modifier)
            ) {
                viewModel.goTo(NavTVDestination.DetailFeature.DetailScreenWithId(it))
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun MainHomeScreenContent(
    modifier: Modifier = Modifier,
    pokemons: List<PokemonListItemDomainModel> = emptyList(),
    ribbons: List<PokeTypesWithChildrenDomainModel> = emptyList(),
    onItemClicked: (String) -> Unit = {}
) {

    val (focusRequester) = remember {
        FocusRequester.createRefs()
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Content(
        modifier = modifier.focusRequester(focusRequester),
        pokemons = pokemons,
        ribbons = ribbons,
        onItemClicked = onItemClicked
    )

}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun Content(
    modifier: Modifier = Modifier,
    pokemons: List<PokemonListItemDomainModel> = emptyList(),
    ribbons: List<PokeTypesWithChildrenDomainModel> = emptyList(),
    state: LazyListState = rememberLazyListState(),
    carouselState: CarouselState = rememberCarouselState(),
    onItemClicked: (String) -> Unit = {}
) {

    LazyColumn(
        modifier = modifier,
        state = state
    ) {
        item {
            Carousel(
                itemCount = pokemons.size,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(LocalConfiguration.current.screenHeightDp.dp * 0.60f),
                carouselState = carouselState,
                carouselIndicator = {
                    CarouselDefaults.IndicatorRow(
                        itemCount = pokemons.size,
                        activeItemIndex = carouselState.activeItemIndex,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(16.dp),
                        indicator = { isActive ->
                            val activeColor = Color.Red
                            val inactiveColor = activeColor.copy(alpha = 0.5f)
                            Box(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .size(8.dp)
                                    .background(
                                        color = if (isActive) activeColor else inactiveColor,
                                        shape = CircleShape,
                                    ),
                            )
                        }
                    )
                },
                contentTransformEndToStart = fadeIn(tween(1000)).togetherWith(
                    fadeOut(
                        tween(
                            1000
                        )
                    )
                ),
                contentTransformStartToEnd = fadeIn(tween(1000)).togetherWith(
                    fadeOut(
                        tween(
                            1000
                        )
                    )
                )
            ) { index ->
                CarouselItem(
                    pokemon = pokemons[index],
                    onItemClicked = onItemClicked
                )
            }
        }
        if (ribbons.isNotEmpty()) {
            items(ribbons.size) { index ->
                PokemonCategory(
                    type = ribbons[index],
                    onItemClicked = onItemClicked
                )
            }
        }
    }
}

@Composable
private fun CarouselItem(pokemon: PokemonListItemDomainModel, onItemClicked: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Black.copy(alpha = 0.4f))
            .clickable(
                role = Role.Button,
                onClick = {
                    onItemClicked(pokemon.name)
                }
            )
    ) {
        AsyncImage(
            model = pokemon.imageUrl,
            //placeholder = painterResource(id = R.drawable.marvel_placeholder),
            contentDescription = "${pokemon.name} outstanding image",
            modifier = Modifier
                .fillMaxSize()
                .blur(20.dp),
            contentScale = ContentScale.Inside
        )
        Text(
            text = pokemon.name,
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}

@Composable
fun PokemonCategory(type: PokeTypesWithChildrenDomainModel, onItemClicked: (String) -> Unit) {

    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = type.type.name.toString(),
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 16.dp)
        )
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(type.children.size) { pokemon ->
                PokeItemView(
                    modifier = Modifier
                        .width(240.dp)
                        .height(180.dp),
                    pokeName = type.children[pokemon].name,
                    pokeImageUrl = type.children[pokemon].imageUrl,
                    contentScale = ContentScale.Inside
                ) { onItemClicked(type.children[pokemon].name) }
            }
        }
    }
}

@Preview(device = "spec:width=1280dp,height=720dp,dpi=240", showBackground = true)
@Composable
fun MainHomeScreenPreview_720p() {
    MainHomeScreenContent()
}

@Preview(device = "spec:width=1920dp,height=1080dp,dpi=240", showBackground = true)
@Composable
fun MainHomeScreenPreview_1080p() {
    MainHomeScreenContent()
}