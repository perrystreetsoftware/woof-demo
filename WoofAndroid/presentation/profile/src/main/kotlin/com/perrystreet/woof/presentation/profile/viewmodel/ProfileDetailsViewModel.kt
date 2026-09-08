package com.perrystreet.woof.presentation.profile.viewmodel

import com.perrystreet.woof.models.dog.Dog
import com.perrystreet.woof.presentation.common.viewmodel.StateProducingViewModel
import com.perrystreet.woof.presentation.profile.mapper.DogProfileDomainToUIModelMapper
import com.perrystreet.woof.presentation.profile.uimodel.ProfileContentUIModel
import com.perrystreet.woof.presentation.profile.uimodel.ProfileDetailsUIModel
import com.perrystreet.woof.usecase.dogs.GetDogProfileUseCase
import io.reactivex.rxjava3.kotlin.plusAssign
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam
import java.util.Optional

@KoinViewModel
class ProfileDetailsViewModel(
    @InjectedParam private val dog: Dog,
    private val getDogProfileUseCase: GetDogProfileUseCase,
    private val profileMapper: DogProfileDomainToUIModelMapper,
) : StateProducingViewModel<ProfileDetailsUIModel>(
    initialValue = ProfileDetailsUIModel(
        name = dog.name,
        summary = null,
        heroTags = emptyList(),
        content = ProfileContentUIModel.Loading,
    ),
) {
    override fun onFirstAppear() {
        disposables += getDogProfileUseCase(dog).subscribe(
            { profile -> _state.onNext(profileMapper(profile)) },
            { error -> mutableError.onNext(Optional.of(error)) },
        )
    }
}
