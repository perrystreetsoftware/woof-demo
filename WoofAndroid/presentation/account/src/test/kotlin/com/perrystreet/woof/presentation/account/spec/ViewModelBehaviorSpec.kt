package com.perrystreet.woof.presentation.account.spec

import com.perrystreet.woof.presentation.account.di.AccountDIModule
import com.perrystreet.woof.testutils.spec.BaseBehaviorSpec
import org.koin.core.module.Module
import org.koin.ksp.generated.module

abstract class ViewModelBehaviorSpec : BaseBehaviorSpec() {
    override val featureModules: List<Module> = listOf(AccountDIModule().module)
}
