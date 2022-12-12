package ir.digireza.tmdb.presentation.app.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import ir.digireza.tmdb.R


sealed class Screen(val route: String, val icon: ImageVector, @StringRes val name: Int) {
    object Home : Screen("Home", Icons.Default.Home, R.string.app_name)
    object Search : Screen("search", Icons.Default.Search, R.string.app_name)
    object Genres : Screen("genres", Icons.Default.Info, R.string.app_name)
}

val bottomNavigatorItems = listOf(
    Screen.Home,
    Screen.Search,
    Screen.Genres,
)