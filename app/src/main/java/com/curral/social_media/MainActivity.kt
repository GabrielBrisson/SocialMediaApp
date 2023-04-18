package com.curral.social_media

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.curral.social_media.domain.repository.SharedPref
import com.curral.social_media.navigation.AppNavigation
import com.curral.social_media.navigation.NavigationRoutes
import com.curral.social_media.ui.theme.SocialMediaTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var sharedPref: SharedPref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SocialMediaTheme {
                val startRoute = if (sharedPref.getUserId() != null) {
                    NavigationRoutes.FEED_ROUTE
                } else {
                    NavigationRoutes.LOGIN_ROUTE
                }

                AppNavigation(startDestination = startRoute)
            }
        }
    }
}
