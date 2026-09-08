package com.perrystreet.woof.presentation.profile.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.perrystreet.woof.presentation.profile.viewmodel.ProfilePagerViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ProfilePagerAdapter(
    dogId: Long,
    viewModel: ProfilePagerViewModel = koinViewModel(key = "profile.pager:$dogId") { parametersOf(dogId) },
) {
    val state by viewModel.state.subscribeAsState()

    ProfilePagerScreen(state = state) { page ->
        ProfileViewAdapter(page = page)
    }
}
