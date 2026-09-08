package com.perrystreet.woof.presentation.account.viewmodel

import com.perrystreet.woof.datasource.account.AccountDataSourceFactory
import com.perrystreet.woof.models.dog.DogSize
import com.perrystreet.woof.presentation.account.spec.ViewModelBehaviorSpec
import com.perrystreet.woof.presentation.account.uimodel.AccountDetailRowUIModel
import com.perrystreet.woof.presentation.account.uimodel.AccountSummaryUIModel
import com.perrystreet.woof.presentation.account.uimodel.AccountUIModel
import com.perrystreet.woof.presentation.account.utils.UiObservableExtensions.test
import com.perrystreet.woof.testutils.rx.lastValue
import com.perrystreet.woof.testutils.scheduler.TimeAdvancingFactory
import org.amshove.kluent.shouldBeEqualTo
import org.koin.test.inject

class AccountViewModelTest : ViewModelBehaviorSpec() {
    private val viewModel: AccountViewModel by inject()

    init {
        Given("I open the account tab") {
            TimeAdvancingFactory().withTestSchedulers()

            Then("The account is loading") {
                viewModel.state.test().lastValue() shouldBeEqualTo AccountViewModel.State.Loading
            }

            When("The account loads") {
                beforeEach {
                    viewModel.onViewAppear()
                    TimeAdvancingFactory().tick()
                }

                Then("My dog's profile is displayed") {
                    viewModel.state.test().lastValue() shouldBeEqualTo AccountViewModel.State.Loaded(
                        account = AccountUIModel(
                            name = "Milo",
                            photoUrl = "file:///android_asset/dogs/border_collie_01.jpg",
                            summary = AccountSummaryUIModel(ageInYears = 3, breed = "Border Collie", neighborhood = "Schöneberg, Berlin"),
                            bio = "Herds tennis balls for a living.",
                            personality = listOf("Frisbee pro", "Early riser"),
                            rows = listOf(
                                AccountDetailRowUIModel.Breed("Border Collie"),
                                AccountDetailRowUIModel.Age(3),
                                AccountDetailRowUIModel.Size(DogSize.Medium),
                                AccountDetailRowUIModel.Neighborhood("Schöneberg, Berlin"),
                                AccountDetailRowUIModel.FavoriteActivity("Agility courses"),
                            ),
                        ),
                    )
                }
            }

            When("The account fails to load") {
                AccountDataSourceFactory().withAccountError()

                beforeEach {
                    viewModel.onViewAppear()
                    TimeAdvancingFactory().tick()
                }

                Then("The account stays loading and an error is emitted") {
                    viewModel.state.test().lastValue() shouldBeEqualTo AccountViewModel.State.Loading
                    viewModel.error.test().lastValue().isPresent shouldBeEqualTo true
                }
            }
        }
    }
}
