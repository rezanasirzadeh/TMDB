package ir.digireza.tmdb.presentation.movies.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ir.digireza.tmdb.domain.entity.MovieDetails
import ir.digireza.tmdb.domain.entity.ProductionCompany
import ir.digireza.tmdb.presentation.movies.details.components.MovieCreditStates
import ir.digireza.tmdb.presentation.shared_components.LoadingComponent

@Composable
fun DetailsCompanyItemComponent(movieDetails: MovieDetails) {

    LazyRow {
        items(items = movieDetails.productionCompanies) { company ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                AsyncImage(
                    model = company.logoPath , contentDescription = null, alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(bottom = 2.dp, start = 4.dp, end = 4.dp)
                        .height(100.dp)

                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
