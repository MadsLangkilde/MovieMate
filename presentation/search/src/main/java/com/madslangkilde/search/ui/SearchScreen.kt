package com.madslangkilde.search.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.madslangkilde.navigation.Navigator
import com.madslangkilde.search.R
import com.madslangkilde.search.viewmodel.MovieItem
import com.madslangkilde.search.viewmodel.SearchScreenIntent
import com.madslangkilde.search.viewmodel.SearchScreenViewModel
import com.madslangkilde.ui_base.theme.Grey300
import com.madslangkilde.ui_base.theme.Grey400
import com.madslangkilde.ui_base.theme.Grey600
import com.madslangkilde.ui_base.theme.Grey800
import com.madslangkilde.ui_base.ui.FavoriteStarIcon
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(navigator: Navigator, viewModel: SearchScreenViewModel = koinViewModel()) {
    val state by viewModel.uiState.collectAsState()
    var query by remember { mutableStateOf("") }
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { -it },
            animationSpec = tween(durationMillis = 500)
        )
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navigator.navigateBack() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
                TextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                        .background(
                            color = Grey300,
                            shape = RoundedCornerShape(32.dp)
                        ),
                    value = query,
                    onValueChange = { query = it },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.searchfield_placeholder),
                            color = Grey600
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {
                        viewModel.onIntent(SearchScreenIntent.SearchMovie(query))
                    }),
                    textStyle = TextStyle(color = Grey800),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent
                    )
                )
            }

            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                if (state.items.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = stringResource(R.string.search_empty_state),
                            style = MaterialTheme.typography.titleMedium,
                            color = Grey400,
                        )
                    }
                } else {
                    HorizontalDivider(color = Grey400, thickness = 1.dp)
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(state.items) { item ->
                            MovieItemCard(item = item, viewModel = viewModel)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MovieItemCard(item: MovieItem, viewModel: SearchScreenViewModel) {
    Spacer(modifier = Modifier.width(4.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Grey300, RoundedCornerShape(8.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = item.imageUrl,
            contentDescription = "Movie Poster",
            modifier = Modifier
                .width(80.dp)
                .height(120.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.poster_placeholder)
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
                horizontalArrangement = Arrangement.Absolute.Right
            ) {
                FavoriteStarIcon(isFavorite = item.isFavorite) {
                    viewModel.onIntent(SearchScreenIntent.OnFavoriteChanged(item))
                }
            }
        }

    }
}