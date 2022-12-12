package ir.digireza.tmdb.presentation.shared_components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ir.digireza.tmdb.R
import ir.digireza.tmdb.presentation.movies.details.components.MoviePicturesState

@Composable
fun DetailsPictureItemComponent(pictures: List<String>, isLoading: Boolean) {
    Box(modifier = Modifier.fillMaxWidth() ) {
        LazyRow {
            items(items = pictures) { picture ->
                Surface(
                    shadowElevation = 10.dp,
                    shape = RoundedCornerShape(20),
                    color = Color.Transparent,
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    AsyncImage(
                        model = picture, contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(top = 0.dp, bottom = 2.dp, start = 4.dp, end = 4.dp)
                            .width(140.dp)
                            .height(140.dp)
                            .clip(RoundedCornerShape(20))
                    )
                }
            }
        }
        AnimatedVisibility(visible = isLoading) {
            LoadingComponent()
        }
    }

}