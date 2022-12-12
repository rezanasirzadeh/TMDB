package ir.digireza.tmdb.presentation.tvshows

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
import ir.digireza.tmdb.app.constants.TvshowType
import ir.digireza.tmdb.app.extensions.getEnumExtra
import ir.digireza.tmdb.presentation.shared_components.LandFilmItemComponent
import ir.digireza.tmdb.presentation.tvshows.viewmodel.AllTvshowsViewModel


@AndroidEntryPoint
class AllTvshowPage : ComponentActivity() {

    private val allTvshowsViewModel: AllTvshowsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pageTitle: String = intent?.getStringExtra(Constant.PAGE_TITLE_KEY) ?: ""
        val bodyContentType: TvshowType =
            intent?.getEnumExtra<TvshowType>() ?: TvshowType.featuredTvshows

        allTvshowsViewModel.getBodyData(bodyContentType)

        setContent {
            BodyContent(
                pageTitle,
                allTvshowsViewModel
            ) { onBackPressedDispatcher.onBackPressed() }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyContent(pageTitle: String, viewModel: AllTvshowsViewModel, onBackPressed: () -> Unit) {
    val tvshowPager = viewModel.tvshowPager.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = pageTitle) },
                navigationIcon = {
                    IconButton(onClick = {

                        onBackPressed()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            items(tvshowPager) { item ->
                item?.let {
                    LandFilmItemComponent(
                        photo = item.posterPath,
                        name = item.name,
                        vote = item.voteAverage.toString(),
                        overview = item.overview
                    )
                }

            }
        }
    }
}
