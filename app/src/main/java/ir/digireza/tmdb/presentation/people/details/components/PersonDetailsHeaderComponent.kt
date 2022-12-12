package ir.digireza.tmdb.presentation.people.details.components

import android.graphics.drawable.AnimatedImageDrawable
import androidx.compose.animation.AnimatedContent
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ir.digireza.tmdb.presentation.shared_components.SpacerComponent


@Composable
fun PersonDetailsHeaderComponent(
    posterPath: String,
    name: String,
    department: String,
    voteCount: String,
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
                .width(IntrinsicSize.Max)

        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 40.dp)
            ) {
                Column(modifier = Modifier.weight(1F)) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    SpacerComponent(height = 4.dp)
                    Text(
                        text = department,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                SpacerComponent(width = 24.dp)
                VotesCompose(voteCount)
            }
        }
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
