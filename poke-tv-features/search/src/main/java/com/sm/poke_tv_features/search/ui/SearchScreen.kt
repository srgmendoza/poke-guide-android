package com.sm.poke_tv_features.search.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchScreenViewModel = koinViewModel<SearchScreenViewModel>()
) {

    val viewState = viewModel.viewState.collectAsState()

    var textFieldValue by remember {
        mutableStateOf(
            ""
        )
    }

    val onSearchAction: (String) -> Unit = {
        textFieldValue = it
        viewModel.searchByName(it)
    }

    SearchScreenContent(
        modifier = modifier
            .fillMaxSize()
            .then(modifier),
        form = viewState.value.form,
        searchFieldValue = textFieldValue,
        onKeywordInput = onSearchAction
    )
}

@Composable
private fun SearchScreenContent(
    modifier: Modifier = Modifier,
    form: SearchScreenViewForm? = null,
    searchFieldValue: String = "",
    onKeywordInput: (String) -> Unit = {}
) {
    val (focusRequester) = remember {
        FocusRequester.createRefs()
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Content(
        modifier = modifier.focusRequester(focusRequester),
        onKeywordInput = onKeywordInput,
        searchFieldValue = searchFieldValue,
        form = form
    )
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    form: SearchScreenViewForm? = null,
    searchFieldValue: String = "",
    onKeywordInput: (String) -> Unit = {}
) {
    Column {
        KeywordInput(
            keyword = searchFieldValue,
            onKeywordInput = onKeywordInput,
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn(
            modifier = modifier,
            state = state,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Box {
                    form?.pokemonName?.let { Text(text = it) }
                }
            }
        }
    }
}

@Composable
private fun KeywordInput(
    keyword: String,
    onKeywordInput: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val textStyle = MaterialTheme.typography.bodyMedium.copy(
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
    val cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurfaceVariant)
    BasicTextField(
        value = keyword,
        onValueChange = onKeywordInput,
        textStyle = textStyle,
        cursorBrush = cursorBrush,
        modifier = modifier,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant,
                        RoundedCornerShape(percent = 50)
                    )
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search Pokemon",
                        modifier = Modifier.padding(end = 12.dp)
                    )
                    innerTextField()
                }
            }
        }
    )
}
