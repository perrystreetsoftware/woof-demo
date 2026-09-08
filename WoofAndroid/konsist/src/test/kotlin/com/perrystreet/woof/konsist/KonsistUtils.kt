package com.perrystreet.woof.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.ext.list.withAnnotationNamed
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.ext.list.withoutAnnotationNamed

object KonsistUtils {
    val productionCode: KoScope
        get() = Konsist.scopeFromProduction()

    val testCode: KoScope
        get() = Konsist.scopeFromTest().slice { !it.path.contains("/konsist/") }

    val designSystemModule: KoScope
        get() = Konsist.scopeFromDirectory("design-system/src/main")

    val atomicDesignModule: KoScope
        get() = designSystemModule.slice { it.path.contains("/atomic/") }

    val presentationModules: KoScope
        get() = productionCode.slice { it.path.contains("/presentation/") }

    val featureModules: KoScope
        get() = presentationModules.slice { !it.path.contains("/presentation/common/") && !it.path.contains("/presentation/navigation/") }

    val dtoModule: KoScope
        get() = Konsist.scopeFromDirectory("dto/src/main")

    val domainModelModule: KoScope
        get() = Konsist.scopeFromDirectory("domain/model/src/main")

    val useCaseModule: KoScope
        get() = Konsist.scopeFromDirectory("domain/usecase/src/main")

    val repositoriesModule: KoScope
        get() = Konsist.scopeFromDirectory("data/repositories/src/main")

    val dataSourceModule: KoScope
        get() = Konsist.scopeFromDirectory("data/datasource/src")

    val konsistRulesFiles
        get() = Konsist.scopeFromDirectory("konsist/src/test").files

    val viewModels: List<KoClassDeclaration>
        get() = featureModules.classes().withNameEndingWith("ViewModel")

    val useCases: List<KoClassDeclaration>
        get() = useCaseModule.classes().withNameEndingWith("UseCase")

    val repositories: List<KoClassDeclaration>
        get() = repositoriesModule.classes().withNameEndingWith("Repository")

    val mappers: List<KoClassDeclaration>
        get() = productionCode.classes().withNameEndingWith("DTOToDomainMapper", "UIModelMapper")

    val composables: List<KoFunctionDeclaration>
        get() = productionCode.functions().withAnnotationNamed("Composable")

    val screenComposables: List<KoFunctionDeclaration>
        get() = featureModules.functions().withAnnotationNamed("Composable").withNameEndingWith("Screen").withoutAnnotationNamed("PreviewDevices")

    val KoFunctionDeclaration.body: String
        get() = text.substringAfter("{", missingDelimiterValue = "")

    val KoClassDeclaration.constructorParameterTypeNames: List<String>
        get() = primaryConstructor?.parameters?.map { it.type.name }.orEmpty()

    fun KoClassDeclaration.hasKoinDefinition(): Boolean =
        hasAnnotationWithName("Single", "Factory", "KoinViewModel")
}
