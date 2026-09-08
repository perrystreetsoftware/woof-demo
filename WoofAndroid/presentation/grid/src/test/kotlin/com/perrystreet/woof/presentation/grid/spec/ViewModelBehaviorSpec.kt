package com.perrystreet.woof.presentation.grid.spec

import com.perrystreet.woof.presentation.grid.di.GridDIModule
import com.perrystreet.woof.testutils.spec.BaseBehaviorSpec
import org.koin.core.module.Module
import org.koin.ksp.generated.module

abstract class ViewModelBehaviorSpec : BaseBehaviorSpec() {
    override val featureModules: List<Module> = listOf(GridDIModule().module)
}
