package com.perrystreet.woof.di

import com.perrystreet.woof.datasource.di.DataSourceDIModule
import com.perrystreet.woof.presentation.account.di.AccountDIModule
import com.perrystreet.woof.presentation.favorites.di.FavoritesDIModule
import com.perrystreet.woof.presentation.grid.di.GridDIModule
import com.perrystreet.woof.presentation.home.di.HomeDIModule
import com.perrystreet.woof.presentation.navigation.di.NavigationDIModule
import com.perrystreet.woof.presentation.profile.di.ProfileDIModule
import com.perrystreet.woof.repositories.di.RepositoriesDIModule
import com.perrystreet.woof.usecase.di.UseCaseDIModule
import org.koin.core.module.Module
import org.koin.ksp.generated.module

object WoofKoinModules {
    val all: List<Module> = listOf(
        AppDIModule().module,
        DataSourceDIModule().module,
        RepositoriesDIModule().module,
        UseCaseDIModule().module,
        NavigationDIModule().module,
        GridDIModule().module,
        ProfileDIModule().module,
        FavoritesDIModule().module,
        AccountDIModule().module,
        HomeDIModule().module,
    )
}
