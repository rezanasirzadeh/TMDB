package ir.digireza.tmdb.presentation.movies.details.components

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ir.digireza.tmdb.R
import ir.digireza.tmdb.presentation.shared_components.SpacerComponent

@Composable
fun HeaderDetailsComponent(
    posterPath: String,
    voteAvg: String,
    voteCount: String,
    state: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        AsyncImage(
            model = posterPath,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(bottom = 50.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 80.dp))
        )
        Surface(
            shadowElevation = 20.dp,
            color = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(bottomStart = 70.dp, topStart = 70.dp),
            modifier = Modifier
                .padding(start = 50.dp)
                .align(Alignment.BottomEnd)
                .height(100.dp)
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 40.dp)
            ) {
                RateCompose(voteAvg)
                VotesCompose(voteCount)
                StateCompose(state)
            }
        }
    }
}

@Composable
fun RateCompose(
    voteAvg: String,

    ) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(imageVector = Icons.Default.Star, contentDescription = "")
        SpacerComponent(height = 4.dp)
        Text(buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontSize = MaterialTheme.typography.labelLarge.fontSize,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(voteAvg)
            }
            withStyle(style = SpanStyle(fontSize = MaterialTheme.typography.labelMedium.fontSize)) {
                append("/10")
            }
        }, color = MaterialTheme.colorScheme.onPrimaryContainer)
    }
}

@Composable
fun VotesCompose(
    voteCount: String,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(imageVector = Icons.Default.Star, contentDescription = "")
        SpacerComponent(height = 4.dp)
        Text(
            "${voteCount}+",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun StateCompose(state: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(imageVector = Icons.Default.Star, contentDescription = "")
        SpacerComponent(height = 4.dp)
        Text(
            state,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}