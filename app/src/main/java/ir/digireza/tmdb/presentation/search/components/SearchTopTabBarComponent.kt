package ir.digireza.tmdb.presentation.search.components

import android.graphics.Paint.Align
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SearchTopTabBarComponent(
    selectedItemIndex: Int,
    tabItems: List<String>,
    onTabTapped: (index: Int) -> Unit
) {

    TabRow(
        selectedTabIndex = selectedItemIndex,
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        indicator = { tabPositions ->
            // TODO: Play with this animation to reach a better animation for indicator
            AnimatedContent(
                targetState = selectedItemIndex,
                transitionSpec = {
                    if (targetState > initialState) {
                        slideInHorizontally(
                            animationSpec = tween(delayMillis = 400),
                            initialOffsetX = { -it }) with
                                fadeOut(animationSpec = tween(delayMillis = 410))
                    } else {
                        slideInHorizontally(
                            animationSpec = tween(delayMillis = 400),
                            initialOffsetX = {
                                it
                            }) with fadeOut(
                            tween(delayMillis = 410),
                        )
                    }

                }
            ) { targetState ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[targetState])
                        .height(7.dp)
                        .padding(horizontal = 20.dp)

                ) {
                    Box(
                        modifier = Modifier
                            .background(color = Color.Red, shape = CircleShape)
                            .width(7.dp)
                            .height(7.dp)
                    ) {

                    }
                }
            }

        },
        divider = {}
    ) {
        tabItems.forEachIndexed { index, text ->
            Tab(
                selected = selectedItemIndex == index,
                onClick = { onTabTapped(index) },
                text = { Text(text = text) })
        }
    }
}

