package com.sm.core.tv_components

import android.util.Log
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.Icon
import androidx.tv.material3.NavigationDrawer
import androidx.tv.material3.NavigationDrawerItem
import androidx.tv.material3.NavigationDrawerScope
import androidx.tv.material3.Text

@Composable
fun NavigationDrawerContainer(
    modifier: Modifier = Modifier,
    currentRoute: String,
    content: @Composable () -> Unit
) {
    NavigationDrawer(
        drawerContent = {
            val isClosed = it == DrawerValue.Closed
            DrawerMenu(currentRoute, isClosed)
        },
        modifier = modifier,
    ) {
        content.invoke()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun NavigationDrawerScope.DrawerMenu(
    currentRoute: String,
    isClosed: Boolean
) {
    val (home, search) = remember { FocusRequester.createRefs() }
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 24.dp)
            .focusProperties {
                enter = {
                    when (currentRoute) {
                        "mainTvDestination" -> home
                        else -> FocusRequester.Default
                    }
                }
            }
            .focusGroup(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Spacer(modifier = Modifier.weight(1f))
        NavigationDrawerItem(
            selected = isClosed && currentRoute == "mainTvDestination",
            onClick = {
                Log.d("TAG", "Search clicked")
            },
            leadingContent = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null
                )
            },
            modifier = Modifier.focusRequester(search)
        ) {
            Text(text = "Search")
        }
        NavigationDrawerItem(
            selected = isClosed && currentRoute == "mainTvDestination",
            onClick = {
                Log.d("TAG", "Home clicked")
            },
            leadingContent = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = null
                )
            },
            modifier = Modifier.focusRequester(home)
        ) {
            Text(text = "Home")
        }
        Spacer(modifier = Modifier.weight(1f))
        // TODO. Add more items here
    }
}