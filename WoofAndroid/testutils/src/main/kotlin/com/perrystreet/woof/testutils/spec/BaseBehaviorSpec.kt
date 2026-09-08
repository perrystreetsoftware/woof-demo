package com.perrystreet.woof.testutils.spec

import com.perrystreet.woof.datasource.di.FakeDataSourceDIModule
import com.perrystreet.woof.repositories.di.RepositoriesDIModule
import com.perrystreet.woof.testutils.scheduler.TestSchedulerProvider
import com.perrystreet.woof.usecase.di.UseCaseDIModule
import com.perrystreet.woof.utils.scheduler.ISchedulerProvider
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.BehaviorSpec
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.ksp.generated.module
import org.koin.test.KoinTest

abstract class BaseBehaviorSpec : BehaviorSpec(), KoinTest {
    abstract val featureModules: List<Module>

    override fun isolationMode() = IsolationMode.InstancePerLeaf

    override suspend fun beforeSpec(spec: Spec) {
        super.beforeSpec(spec)
        startKoin {
            modules(coreTestModules + featureModules)
        }
    }

    override suspend fun afterSpec(spec: Spec) {
        super.afterSpec(spec)
        stopKoin()
    }

    private companion object {
        val schedulerModule = module {
            single { TestSchedulerProvider() } bind ISchedulerProvider::class
        }

        val coreTestModules: List<Module> = listOf(
            schedulerModule,
            FakeDataSourceDIModule().module,
            RepositoriesDIModule().module,
            UseCaseDIModule().module,
        )
    }
}
