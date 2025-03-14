package com.madslangkilde.movielist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.madslangkilde.movielist.R
import com.madslangkilde.movielist.viewmodel.FavoriteMovieItem
import com.madslangkilde.movielist.viewmodel.MovieListScreenIntent
import com.madslangkilde.movielist.viewmodel.MovieListScreenViewModel
import com.madslangkilde.navigation.NavigationRoute
import com.madslangkilde.navigation.Navigator
import com.madslangkilde.ui_base.theme.Grey300
import com.madslangkilde.ui_base.theme.Grey400
import org.koin.androidx.compose.koinViewModel
import com.madslangkilde.moviemate.uibase.R as UibaseR

@Composable
fun MovieListScreen(navigator: Navigator, viewModel: MovieListScreenViewModel = koinViewModel()) {
    val state by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        if (state.items.isEmpty()) {
            EmptyFavoritesScreen { navigator.navigateTo(NavigationRoute.SEARCH) }
        } else {
            HorizontalDivider(color = Grey400, thickness = 1.dp)
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(state.items) { item ->
                    FavoriteMovieItemCard(item = item, viewModel = viewModel, onItemClicked = {
                        navigator.navigateTo(NavigationRoute.EDIT_FAVORITE_MOVIE_NOTE.replace("{id}", item.movieId))
                    })
                }
            }
        }
    }
}

@Composable
fun EmptyFavoritesScreen(onButtonClicked: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.favorites_empty_state),
                style = MaterialTheme.typography.titleMedium,
                color = Grey400,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { onButtonClicked() }) {
                Text(text = stringResource(R.string.favorites_empty_state_button))
            }
        }
    }
}

@Composable
fun FavoriteMovieItemCard(
    item: FavoriteMovieItem, viewModel: MovieListScreenViewModel,
    onItemClicked: (FavoriteMovieItem) -> Unit
) {
    Spacer(modifier = Modifier.width(4.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Grey300, RoundedCornerShape(8.dp))
            .padding(8.dp)
            .clickable { onItemClicked(item) },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = item.poster,
            contentDescription = "Movie Poster",
            modifier = Modifier
                .width(80.dp)
                .height(120.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = UibaseR.drawable.poster_placeholder)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.align(Alignment.Top)) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item.title, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = item.year)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = item.rating)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.Right,
            ) {
                IconButton(onClick = {
                    viewModel.onIntent(
                        MovieListScreenIntent.OnRemoveFavoriteClicked(item)
                    )
                }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Favorite",
                        tint = Color.Black,
                        modifier = Modifier.size(46.dp)
                    )
                }
            }
        }

    }
}