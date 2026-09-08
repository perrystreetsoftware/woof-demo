package com.perrystreet.woof.datasource.account

import org.koin.test.KoinTest
import org.koin.test.inject

class AccountDataSourceFactory : KoinTest {
    private val dataSource: FakeAccountDataSource by inject()

    fun withAccountError() = apply {
        dataSource.getAccountError = IllegalStateException("Could not load account")
    }
}
