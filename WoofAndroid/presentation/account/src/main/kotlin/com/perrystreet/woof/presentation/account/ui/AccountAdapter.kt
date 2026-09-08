package com.perrystreet.woof.presentation.account.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.perrystreet.woof.presentation.account.viewmodel.AccountViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AccountAdapter(viewModel: AccountViewModel = koinViewModel()) {
    val state by viewModel.state.subscribeAsState()

    LaunchedEffect(Unit) {
        viewModel.onViewAppear()
    }

    AccountScreen(state = state)
}
