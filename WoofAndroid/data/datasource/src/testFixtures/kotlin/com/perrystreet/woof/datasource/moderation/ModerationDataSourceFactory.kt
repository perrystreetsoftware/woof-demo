package com.perrystreet.woof.datasource.moderation

import org.koin.test.KoinTest
import org.koin.test.inject

class ModerationDataSourceFactory : KoinTest {
    private val dataSource: FakeModerationDataSource by inject()

    fun withBlockDogError() = apply {
        dataSource.blockDogError = IllegalStateException("Could not block dog")
    }
}
