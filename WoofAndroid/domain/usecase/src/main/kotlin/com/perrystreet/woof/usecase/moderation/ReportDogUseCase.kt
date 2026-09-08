package com.perrystreet.woof.usecase.moderation

import com.perrystreet.woof.models.dog.Dog
import com.perrystreet.woof.repositories.moderation.ModerationRepository
import io.reactivex.rxjava3.core.Completable
import org.koin.core.annotation.Factory

@Factory
class ReportDogUseCase(
    private val moderationRepository: ModerationRepository,
) {
    operator fun invoke(dog: Dog): Completable = moderationRepository.addReport(dog)
}
