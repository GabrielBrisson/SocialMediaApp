package com.curral.social_media.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.curral.social_media.presentation.feed.FeedScreen
import com.curral.social_media.presentation.login.LoginScreen
import com.curral.social_media.presentation.post.PostScreen
import com.curral.social_media.presentation.profile.ProfileScreen
import com.curral.social_media.presentation.users.UsersScreen

object NavigationScreens {
    const val FEED_SCREEN = "feed"
    const val PROFILE_SCREEN = "profile"
    const val LOGIN_SCREEN = "login"
    const val USERS_SCREEN = "users"
    const val POST_SCREEN = "post"
}

object NavigationArgs {
    const val USER_ID = "user_id"
}

object NavigationRoutes {
    const val FEED_ROUTE = NavigationScreens.FEED_SCREEN
    const val PROFILE_ROUTE = "${NavigationScreens.PROFILE_SCREEN}/{${NavigationArgs.USER_ID}}"
    const val LOGIN_ROUTE = NavigationScreens.LOGIN_SCREEN
    const val USERS_ROUTE = NavigationScreens.USERS_SCREEN
    const val POST_ROUTE = NavigationScreens.POST_SCREEN
}

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavigationRoutes.FEED_ROUTE
) {
    val activity = (LocalContext.current as? Activity)
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = NavigationRoutes.FEED_ROUTE) {
            BackHandler(true) {
                activity?.finish()
            }
            FeedScreen(
                onAddFriend = { navController.navigate(NavigationRoutes.USERS_ROUTE) },
                onProfile = { userId ->
                    val route = "${NavigationScreens.PROFILE_SCREEN}/$userId"
                    navController.navigate(route)
                },
                onPost = { navController.navigate(NavigationRoutes.POST_ROUTE) }
            )
        }

        composable(
            route = NavigationRoutes.PROFILE_ROUTE,
            arguments = listOf(
                navArgument(name = NavigationArgs.USER_ID) { type = NavType.StringType }
            )
        ) {
            ProfileScreen(
                onAddFriend = { navController.navigate(NavigationRoutes.USERS_ROUTE) },
                onBack = navController::navigateUp,
            )
        }

        composable(route = NavigationRoutes.LOGIN_ROUTE) {
            LoginScreen(
                goToFeed = { navController.navigate(NavigationRoutes.FEED_ROUTE) }
            )
        }

        composable(route = NavigationRoutes.USERS_ROUTE) {
            UsersScreen(
                onBack = navController::navigateUp
            )
        }

        composable(route = NavigationRoutes.POST_ROUTE) {
            PostScreen(onBack = { navController.navigateUp() })
        }
    }

}
