package com.perrystreet.woof.presentation.common.error

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.res.stringResource
import com.perrystreet.woof.designsystem.atomic.organisms.toast.OrgToastHost
import com.perrystreet.woof.presentation.common.viewmodel.base.ErrorProducingViewModel
import io.reactivex.rxjava3.core.Observable
import java.util.Optional

@Composable
fun ErrorAdapter(
    viewModels: List<ErrorProducingViewModel>,
    errorMapper: IErrorToToastMapper,
) {
    val errorToast by remember(viewModels, errorMapper) {
        Observable.merge(viewModels.map { viewModel -> viewModel.error })
            .map { error -> error.flatMap { throwable -> Optional.ofNullable(errorMapper(throwable)) } }
    }.subscribeAsState(initial = Optional.empty<ErrorToast>())

    OrgToastHost(
        message = errorToast.orElse(null)?.text(),
        onDismiss = { viewModels.forEach { viewModel -> viewModel.clearLastError() } },
    )
}

@Composable
private fun ErrorToast.text(): String = stringResource(messageRes, *formatArgs.toTypedArray())
