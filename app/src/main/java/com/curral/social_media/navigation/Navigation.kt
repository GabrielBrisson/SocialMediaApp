package com.curral.social_media.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.curral.social_media.presentation.feed.FeedScreen
import com.curral.social_media.presentation.profile.ProfileScreen

object NavigationScreens {
    const val FEED_SCREEN = "feed"
    const val PROFILE_SCREEN = "profile"
}

object NavigationArgs {
    const val USER_ID = "user_id"
}

object NavigationRoutes {
    const val FEED_ROUTE = NavigationScreens.FEED_SCREEN
    const val PROFILE_ROUTE = "${NavigationScreens.PROFILE_SCREEN}/{${NavigationArgs.USER_ID}}"
}

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavigationRoutes.FEED_ROUTE
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = NavigationRoutes.FEED_ROUTE) {
            FeedScreen(
                onProfile = { userId ->
                    val route = "${NavigationScreens.PROFILE_SCREEN}/$userId"
                    navController.navigate(route)
                }
            )
        }

        composable(
            route = NavigationRoutes.PROFILE_ROUTE,
            arguments = listOf(
                navArgument(name = NavigationArgs.USER_ID) { type = NavType.IntType }
            )
        ) {
            ProfileScreen(
                onBack = navController::navigateUp,
            )
        }

    }

}