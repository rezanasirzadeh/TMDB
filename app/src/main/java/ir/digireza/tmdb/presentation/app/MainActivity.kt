package ir.digireza.tmdb.presentation.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.digireza.tmdb.R
import ir.digireza.tmdb.presentation.app.navigation.Screen
import ir.digireza.tmdb.presentation.app.navigation.bottomNavigatorItems
import ir.digireza.tmdb.presentation.dashboard.DashboardPage
import ir.digireza.tmdb.presentation.genres.GenresPage
import ir.digireza.tmdb.presentation.search.SearchPage
import ir.digireza.tmdb.ui.theme.TMDBTheme


@AndroidEntryPoint
class MainActivity() : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMDBTheme {
                DashboardScreen()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    val navController = rememberNavController()
    val navBackStray by navController.currentBackStackEntryAsState()


    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            NavigationBar(
                modifier = Modifier.fillMaxWidth()
            ) {
                bottomNavigatorItems.forEach { screen ->
                    NavigationBarItem(
                        selected = navBackStray?.destination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = ""
                            )
                        })
                }
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    bottom = innerPadding.calculateBottomPadding(),
                )
        ) {

            ContentCompose(navController)
        }

    }
}

@Composable
fun ContentCompose(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { DashboardPage() }
        composable(Screen.Search.route) { SearchPage() }
        composable(Screen.Genres.route) { GenresPage() }
    }
}


// TODO: Implement custom shadow for Images
/*fun Modifier.drawColoredShadow(
    color: Color,
    alpha: Float = 0.2f,
    borderRadius: Dp = 0.dp,
    shadowRadius: Dp = 20.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp
) = this.drawBehind {
    val transparentColor = android.graphics.Color.toArgb(color.copy(alpha = 0.0f).value.toLong())
    val shadowColor = android.graphics.Color.toArgb(color.copy(alpha = alpha).value.toLong())
    this.drawIntoCanvas {
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.color = transparentColor
        frameworkPaint.setShadowLayer(
            shadowRadius.toPx(),
            offsetX.toPx(),
            offsetY.toPx(),
            shadowColor
        )
        it.drawRoundRect(
            0f,
            0f,
            this.size.width,
            this.size.height,
            borderRadius.toPx(),
            borderRadius.toPx(),
            paint
        )
    }
}*/

@Composable
fun MyButton() {
    Box(
        modifier = Modifier
            .size(150.dp)
            .background(
                shape = RoundedCornerShape(20),
                alpha = 1F,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Red,
                        Color.Red,
                        Color.Transparent
                    )
                )
            )
            .padding(bottom = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            shape = RoundedCornerShape(20),
            modifier = Modifier
                .size(55.dp)
                .background(Color.Transparent)
                .clickable { }
        ) {
            Image(
                painter = painterResource(id = R.drawable.film_cover),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
        }

    }
}
