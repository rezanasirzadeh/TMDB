package ir.digireza.tmdb.presentation.people.show_all

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dagger.hilt.android.AndroidEntryPoint
import ir.digireza.tmdb.app.constants.Constant
import ir.digireza.tmdb.presentation.people.show_all.viewmodel.AllPeopleViewModel
import ir.digireza.tmdb.presentation.shared_components.LandPeopleItemComponent


@AndroidEntryPoint
class AllPeoplePage : ComponentActivity() {

    private val allPeopleViewModel: AllPeopleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pageTitle: String = intent?.getStringExtra(Constant.PAGE_TITLE_KEY) ?: ""
        allPeopleViewModel.getBodyData()


        setContent {
            BodyContent(
                pageTitle,
                allPeopleViewModel
            ) { onBackPressedDispatcher.onBackPressed() }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyContent(pageTitle: String, viewModel: AllPeopleViewModel, onBackPressed: () -> Unit) {
    val peoplePager = viewModel.peoplePager.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = pageTitle) },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            items(peoplePager) { item ->
                item?.let {
                    LandPeopleItemComponent(
                        photo = item.profilePath ?: "",
                        name = item.name,
                        popularity = item.popularity.toString(),
                        knownFor = item.knownFor
                    )
                }

            }
        }
    }
}

