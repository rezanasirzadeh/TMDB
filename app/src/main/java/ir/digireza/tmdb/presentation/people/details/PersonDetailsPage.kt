package ir.digireza.tmdb.presentation.people.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import ir.digireza.tmdb.app.constants.Constant
import ir.digireza.tmdb.domain.entity.People
import ir.digireza.tmdb.presentation.people.details.components.PersonDetailsHeaderComponent
import ir.digireza.tmdb.presentation.people.details.viewmodel.PersonDetailsViewModel
import ir.digireza.tmdb.presentation.shared_components.DetailsHeadlineTextComponent
import ir.digireza.tmdb.presentation.shared_components.DetailsPictureItemComponent
import ir.digireza.tmdb.presentation.shared_components.LoadingComponent
import ir.digireza.tmdb.presentation.shared_components.SpacerComponent


@AndroidEntryPoint
class PersonDetailsPage : ComponentActivity() {

    private val viewModel: PersonDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val personId = intent.getStringExtra(Constant.CLICKED_ITEM_ID) ?: "0"

        lifecycleScope.launchWhenStarted {
            viewModel.getPersonDetails(personId = personId.toInt())
            viewModel.getPersonPictures(personId = personId.toInt())
        }

        setContent {
            Box {
                DetailsBody(viewModel)
                IconButton(
                    modifier = Modifier.align(Alignment.TopStart),
                    onClick = { onBackPressedDispatcher.onBackPressed() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                    )
                }
                AnimatedVisibility(visible = viewModel.personDetailsState.isLoading) {
                    LoadingComponent()
                }
            }
        }
    }
}

@Composable
fun DetailsBody(viewModel: PersonDetailsViewModel) {
    Column(modifier = Modifier.verticalScroll(state = rememberScrollState())) {
        viewModel.personDetailsState.people?.let {
            PersonDetailsHeaderComponent(
                it.profilePath ?: "",
                it.name,
                it.knownFor,
                it.popularity.toString()
            )
        }
        SpacerComponent(height = 40.dp)
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            viewModel.personDetailsState.people?.let { PrimaryPeopleDetailsSection(it) }
        }
        SpacerComponent(height = 30.dp)
        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            DetailsHeadlineTextComponent("Pictures")
        }
        SpacerComponent(height = 10.dp)
        DetailsPictureItemComponent(
            viewModel.personPicturesState.peoplePictures,
            viewModel.personPicturesState.isLoading
        )
        SpacerComponent(height = 40.dp)
    }
}

@Composable
fun PrimaryPeopleDetailsSection(person: People) {
    DetailsHeadlineTextComponent("Biography")
    SpacerComponent(height = 10.dp)
    Text(
        text = person.placeOfBirth ?: "",
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onBackground
    )
    SpacerComponent(height = 10.dp)
    Text(
        text = person.biography ?: "",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onBackground
    )
}