package com.perrystreet.woof.presentation.profile.spec

import com.perrystreet.woof.presentation.navigation.INavigator
import com.perrystreet.woof.presentation.profile.di.ProfileDIModule
import com.perrystreet.woof.presentation.profile.navigation.FakeNavigator
import com.perrystreet.woof.testutils.spec.BaseBehaviorSpec
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.ksp.generated.module

abstract class ViewModelBehaviorSpec : BaseBehaviorSpec() {
    override val featureModules: List<Module> = listOf(ProfileDIModule().module, fakeNavigationModule)

    private companion object {
        val fakeNavigationModule = module {
            single { FakeNavigator() } bind INavigator::class
        }
    }
}
