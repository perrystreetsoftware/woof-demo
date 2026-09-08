package com.perrystreet.woof.presentation.common.error

interface IErrorToToastMapper {
    operator fun invoke(error: Throwable): ErrorToast?
}
