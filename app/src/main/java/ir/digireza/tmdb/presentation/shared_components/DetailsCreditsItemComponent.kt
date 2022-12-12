package ir.digireza.tmdb.presentation.movies.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ir.digireza.tmdb.presentation.movies.details.components.MovieCreditStates
import ir.digireza.tmdb.presentation.shared_components.LoadingComponent

@Composable
fun DetailsCreditsItemComponent(movieCreditStates: MovieCreditStates) {
    Box {
        LazyRow {
            items(items = movieCreditStates.movieCredits) { credit ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(100.dp)
                ) {
                    Surface(
                        shadowElevation = 10.dp,
                        shape = CircleShape,
                        color = Color.Transparent,
                    ) {
                        AsyncImage(
                            model = credit.posterPath, contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(bottom = 2.dp, start = 4.dp, end = 4.dp)
                                .width(80.dp)
                                .height(80.dp)
                                .clip(CircleShape)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = credit.name,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = credit.knownFor,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(start = 10.dp, end = 10.dp)
                    )
                }
            }
        }

        AnimatedVisibility(visible = movieCreditStates.isLoading) {
            LoadingComponent()
        }
    }

}