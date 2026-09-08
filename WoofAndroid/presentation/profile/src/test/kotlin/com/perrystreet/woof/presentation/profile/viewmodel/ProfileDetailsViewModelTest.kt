package com.perrystreet.woof.presentation.profile.viewmodel

import com.perrystreet.woof.datasource.dogs.DogsDataSourceFactory
import com.perrystreet.woof.models.dog.DogSize
import com.perrystreet.woof.presentation.profile.factory.DogFactory
import com.perrystreet.woof.presentation.profile.spec.ViewModelBehaviorSpec
import com.perrystreet.woof.presentation.profile.uimodel.ProfileContentUIModel
import com.perrystreet.woof.presentation.profile.uimodel.ProfileDetailRowUIModel
import com.perrystreet.woof.presentation.profile.uimodel.ProfileDetailsUIModel
import com.perrystreet.woof.presentation.profile.uimodel.ProfileSectionUIModel
import com.perrystreet.woof.presentation.profile.uimodel.ProfileSummaryUIModel
import com.perrystreet.woof.presentation.profile.utils.UiObservableExtensions.test
import com.perrystreet.woof.testutils.rx.lastValue
import com.perrystreet.woof.testutils.scheduler.TimeAdvancingFactory
import org.amshove.kluent.shouldBeEqualTo
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

class ProfileDetailsViewModelTest : ViewModelBehaviorSpec() {
    private val dog = DogFactory().withId(1).produce()
    private val viewModel: ProfileDetailsViewModel by inject { parametersOf(dog) }

    init {
        Given("I open a dog profile") {
            TimeAdvancingFactory().withTestSchedulers()
            DogsDataSourceFactory().withDogs(count = 3)

            Then("The name shows instantly while the rest of the profile is loading") {
                viewModel.state.test().lastValue() shouldBeEqualTo ProfileDetailsUIModel(
                    name = "Dog 1",
                    summary = null,
                    heroTags = emptyList(),
                    content = ProfileContentUIModel.Loading,
                )
            }

            When("The profile loads") {
                beforeEach {
                    viewModel.onViewAppear()
                    TimeAdvancingFactory().tick()
                }

                Then("The full profile is displayed") {
                    viewModel.state.test().lastValue() shouldBeEqualTo ProfileDetailsUIModel(
                        name = "Dog 1",
                        summary = ProfileSummaryUIModel(
                            ageInYears = 4,
                            breed = "Golden Retriever",
                            neighborhood = "Kolonaki, Athens",
                        ),
                        heroTags = listOf("Playful", "Cuddly"),
                        content = ProfileContentUIModel.Visible(
                            sections = listOf(
                                ProfileSectionUIModel.About(
                                    name = "Dog 1",
                                    bio = "Loves swimming, tennis balls, and stealing snacks.",
                                ),
                                ProfileSectionUIModel.Personality(tags = listOf("Playful", "Cuddly")),
                                ProfileSectionUIModel.Details(
                                    rows = listOf(
                                        ProfileDetailRowUIModel.Breed("Golden Retriever"),
                                        ProfileDetailRowUIModel.Age(4),
                                        ProfileDetailRowUIModel.Size(DogSize.Large),
                                        ProfileDetailRowUIModel.Neighborhood("Kolonaki, Athens"),
                                        ProfileDetailRowUIModel.FavoriteActivity("Swimming at the beach"),
                                    ),
                                ),
                            ),
                        ),
                    )
                }
            }

            When("The profile fails to load") {
                DogsDataSourceFactory().withDogProfileError()

                beforeEach {
                    viewModel.onViewAppear()
                    TimeAdvancingFactory().tick()
                }

                Then("The profile stays in the loading state and an error is emitted") {
                    viewModel.state.test().lastValue().content shouldBeEqualTo ProfileContentUIModel.Loading
                    viewModel.error.test().lastValue().isPresent shouldBeEqualTo true
                }
            }
        }
    }
}
