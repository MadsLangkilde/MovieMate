package com.madslangkilde.ui_base.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.madslangkilde.ui_base.theme.Yellow800

@Composable
fun FavoriteStarIcon(isFavorite: Boolean, onFavoriteChanged: (Boolean) -> Unit) {
    IconButton(onClick = { onFavoriteChanged(!isFavorite) }) {
        Icon(
            imageVector = if (isFavorite) Icons.Filled.Star else Icons.Outlined.Star,
            contentDescription = "Favorite",
            tint = if (isFavorite) Yellow800 else Color.Black,
            modifier = Modifier.size(36.dp)
        )
    }
}
