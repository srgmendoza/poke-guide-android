package com.sm.poke_tv_features.listing.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Carousel
import androidx.tv.material3.CarouselDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import androidx.tv.material3.rememberCarouselState
import coil3.compose.AsyncImage
import com.sm.core.ui.commons.onFocusLogger
import com.sm.core.ui.components.PokeItemView
import com.sm.poke_domain.models.PokeTypesWithChildrenDomainModel
import com.sm.poke_domain.models.PokemonListItemDomainModel

@Composable
fun MainHomeScreen(viewModel: MainHomeScreenViewModel) {
    val viewState = viewModel.viewState.collectAsState()
    when (viewState.value) {
        is MainHomeScreenViewState.Initial -> {
            viewModel.fetchContent()
        }
        else -> {
            MainHomeScreenContent(
                pokemons = viewState.value.form.pokemons,
                ribbons = viewState.value.form.ribbons
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun MainHomeScreenContent(
    pokemons: List<PokemonListItemDomainModel> = emptyList(),
    ribbons: List<PokeTypesWithChildrenDomainModel> = emptyList()
) {
    val carouselState = rememberCarouselState()
    var isExpanded by remember { mutableStateOf(true) }

    val sidebarFocusRequester = remember { FocusRequester() }
    val contentFocusRequester = remember { FocusRequester() }
    val carouselFocusRequester = remember { FocusRequester() }
    val categoryFocusRequester = remember { List(19) { FocusRequester() } }

    LaunchedEffect(isExpanded) {
        if (isExpanded) {
            sidebarFocusRequester.requestFocus()
        } else {
            contentFocusRequester.requestFocus()
        }
    }

    Surface {
        Row {
            AnimatedVisibility(
                visible = isExpanded,
                enter = androidx.compose.animation.expandHorizontally(animationSpec = tween(300)),
                exit = androidx.compose.animation.shrinkHorizontally(animationSpec = tween(300))
            ) {
                SidebarContent(
                    onClose = {
                        isExpanded = false
                    },
                    focusRequester = sidebarFocusRequester
                )
            }
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .focusRequester(contentFocusRequester)
                .focusable()
                .onKeyEvent { keyEvent ->
                    if (keyEvent.type == KeyEventType.KeyDown) {
                        when (keyEvent.key) {
                            Key.DirectionLeft -> {
                                isExpanded = true; true
                            }
                            Key.DirectionUp -> {
                                carouselFocusRequester.requestFocus(); true
                            }
                            Key.DirectionDown -> {
                                if (ribbons.isNotEmpty()) {
                                    categoryFocusRequester[0].requestFocus()
                                }
                                true
                            }
                            else -> false
                        }
                    } else false
                }
                .onFocusLogger("Content")
            ) {
                item {
                    Carousel(
                        itemCount = pokemons.size,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(LocalConfiguration.current.screenHeightDp.dp * 0.60f)
                            .focusRequester(carouselFocusRequester)
                            .focusable()
                            .onKeyEvent { keyEvent ->
                                if (keyEvent.type == KeyEventType.KeyDown) {
                                    when (keyEvent.key) {
                                        Key.DirectionDown -> {
                                            if (ribbons.isNotEmpty()) {
                                                categoryFocusRequester[0].requestFocus()
                                            }
                                            true
                                        }
                                        else -> false
                                    }
                                } else false
                            }
                            .onFocusLogger("Carousel"),
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
                            pokemon = pokemons[index]
                        )
                    }
                }
                if (ribbons.isNotEmpty()) {
                    items(ribbons.size) { index ->
                        PokemonCategory(
                            type = ribbons[index],
                            focusRequester = categoryFocusRequester[index]
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CarouselItem(pokemon: PokemonListItemDomainModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Black.copy(alpha = 0.4f))
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
fun PokemonCategory(type: PokeTypesWithChildrenDomainModel, focusRequester: FocusRequester) {
    val cardFocusRequester = remember { List(type.children.size) { FocusRequester() } }

    Column(modifier = Modifier
        .padding(vertical = 8.dp)
        .focusRequester(focusRequester)
        .focusable()
        .onKeyEvent { keyEvent ->
            if (keyEvent.type == KeyEventType.KeyDown) {
                when (keyEvent.key) {
                    Key.DirectionUp -> {
                        focusRequester.freeFocus() // Vuelve al carrusel si es la primera categoría
                        true
                    }
                    else -> false
                }
            } else false
        }
        .onFocusLogger("PokemonCategory")
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
                        .height(180.dp)
                        .focusRequester(cardFocusRequester[pokemon])
                        .focusable()
                        .onKeyEvent { keyEvent ->
                            if (keyEvent.type == KeyEventType.KeyDown) {
                                when (keyEvent.key) {
                                    Key.DirectionRight -> true // Permite moverse lateralmente
                                    Key.DirectionLeft -> true
                                    else -> false
                                }
                            } else false
                        }
                        .onFocusLogger("Pokemon Category Item"),
                    pokeName = type.children[pokemon].name,
                    pokeImageUrl = type.children[pokemon].imageUrl,
                    contentScale = ContentScale.Inside
                ) { }
            }
        }
    }
}

@Composable
fun SidebarContent(onClose: () -> Unit, focusRequester: FocusRequester) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .fillMaxHeight()
            .background(
                Color.Black.copy(alpha = 0.8f),
                shape = RoundedCornerShape(0.dp, 12.dp, 12.dp, 0.dp)
            )
            .focusRequester(focusRequester)
            .focusable()
            .onKeyEvent { keyEvent ->
                if (keyEvent.type == KeyEventType.KeyDown) {
                    when (keyEvent.key) {
                        Key.DirectionRight -> {
                            onClose()
                            true
                        }  // Cierra el menú y cambia el foco
                        else -> true
                    }
                } else true
            }
            .onFocusLogger("Sidebar"),
        verticalArrangement = Arrangement.Center
    ) {
        val itemFocusRequester = remember { List(4) { FocusRequester() } }

        SidebarItem("🏠 Home", itemFocusRequester[0])
        SidebarItem("🔍 Search", itemFocusRequester[1])
        SidebarItem("⭐ Favorites", itemFocusRequester[2])
        SidebarItem("⚙️ Settings", itemFocusRequester[3])

        // Al abrir el menú, el primer ítem toma el foco
        LaunchedEffect(Unit) {
            itemFocusRequester[0].requestFocus()
        }
    }
}

@Composable
fun SidebarItem(text: String, focusRequester: FocusRequester) {
    Text(
        text = text,
        fontSize = 20.sp,
        color = Color.White,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .focusable()
            .background(Color.Gray.copy(alpha = 0.2f))
            .onFocusLogger("Sidebar Item")
    )
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