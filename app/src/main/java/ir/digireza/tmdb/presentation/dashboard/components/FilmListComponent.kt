package ir.digireza.tmdb.presentation.dashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ir.digireza.tmdb.R
import ir.digireza.tmdb.domain.entity.Movie


@Composable
fun FilmItem(title: String , coverPhoto: String , voteAverage: String , onItemClick: ()->Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .width(200.dp)
            .height(300.dp)
            .clickable { onItemClick() }
    ) {
        Surface(
            shadowElevation = 10.dp,
            shape = RoundedCornerShape(20),
            color = Color.Transparent
        ) {
            AsyncImage(
                model = coverPhoto, contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(200.dp)
                    .height(250.dp)
                    .padding(top = 0.dp, bottom = 2.dp, start = 4.dp, end = 4.dp)
                    .clip(RoundedCornerShape(20))
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(start = 10.dp, end = 10.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = voteAverage,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "",
                tint = Color.Red,
                modifier = Modifier.size(12.dp)
            )
        }
    }
}

@Composable
fun HomeListHeader(title: String, onSeeAllClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp
            )
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.weight(1F)
        )
        Text(
            text = "See all",
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.clickable {
                onSeeAllClick()
            }
        )
    }
}