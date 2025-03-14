package com.madslangkilde.moviemate.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.madslangkilde.moviemate.R
import com.madslangkilde.navigation.NavigationRoute
import com.madslangkilde.navigation.Navigator
import com.madslangkilde.ui_base.theme.Blue500
import kotlinx.coroutines.launch
import com.madslangkilde.moviemate.uibase.R as UibaseR

data class DrawerItem(
    val route: String,
    val title: Int,
    val icon: ImageVector
)

val drawerItems = listOf(
    DrawerItem(NavigationRoute.START, R.string.home, Icons.Filled.Home),
    DrawerItem(NavigationRoute.MOVIELIST, R.string.movielist, Icons.Filled.Favorite),
    DrawerItem(NavigationRoute.SEARCH, R.string.search, Icons.Filled.Search)
)

@Composable
fun MainScreen(navController: NavHostController) {
    val navigator = remember { Navigator(navController) }
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: NavigationRoute.START
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItem by remember { mutableStateOf(NavigationRoute.START) }
    val topBarState = rememberSaveable { (mutableStateOf(currentRoute != NavigationRoute.SEARCH)) }

    LaunchedEffect(currentRoute) {
        topBarState.value = currentRoute != NavigationRoute.SEARCH
    }

    val myDrawerItemColors = NavigationDrawerItemDefaults.colors(
        unselectedContainerColor = Color.Transparent,
        selectedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
        unselectedIconColor = Color.DarkGray,
        selectedIconColor = MaterialTheme.colorScheme.primary,
        unselectedTextColor = Color.DarkGray,
        selectedTextColor = MaterialTheme.colorScheme.primary
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerHeader()
                Spacer(Modifier.height(8.dp))

                drawerItems.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item.icon, contentDescription = stringResource(item.title)) },
                        label = { Text(stringResource(item.title)) },
                        selected = currentRoute == item.route,
                        onClick = {
                            navigator.navigateTo(item.route)
                            selectedItem = item.route
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                        colors = myDrawerItemColors
                    )
                }
            }
        },
        content = {
            Scaffold(
                topBar = {
                    TopBar(topBarState, { currentRoute }, {
                        scope.launch {
                            drawerState.open()
                        }
                    })
                }
            ) { innerPadding ->
                Navigation(navController, navigator, modifier = Modifier.padding(innerPadding))
            }
        }
    )
}

@Composable
fun DrawerHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Blue500)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = UibaseR.drawable.ic_app_white),
                contentDescription = stringResource(R.string.description_app_icon_text),
                modifier = Modifier.height(40.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    topBarState: MutableState<Boolean>,
    currentRoute: @Composable () -> String,
    navigationIconClicked: () -> Unit
) {
    AnimatedVisibility(
        visible = topBarState.value,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it }),
        content = {
            TopAppBar(
                title = {
                    if (currentRoute() == NavigationRoute.START) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = UibaseR.drawable.ic_app_white),
                                contentDescription = stringResource(R.string.description_app_icon),
                                modifier = Modifier.padding(
                                    end = 8.dp,
                                    top = 14.dp,
                                    bottom = 14.dp
                                )
                            )
                            Text(
                                text = stringResource(R.string.app_name),
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White
                            )
                        }
                    } else {
                        val title = if (currentRoute() == NavigationRoute.MOVIELIST) {
                            stringResource(R.string.movielist)
                        } else {
                            ""
                        }
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }

                },
                navigationIcon = {
                    IconButton(onClick = {
                        navigationIconClicked()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = stringResource(R.string.menu),
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    )
}

