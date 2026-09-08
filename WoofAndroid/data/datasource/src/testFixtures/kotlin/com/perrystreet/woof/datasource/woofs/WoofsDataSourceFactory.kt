package com.perrystreet.woof.datasource.woofs

import org.koin.test.KoinTest
import org.koin.test.inject

class WoofsDataSourceFactory : KoinTest {
    private val dataSource: FakeWoofsDataSource by inject()

    fun withSendWoofError() = apply {
        dataSource.sendWoofError = IllegalStateException("Could not send woof")
    }
}
