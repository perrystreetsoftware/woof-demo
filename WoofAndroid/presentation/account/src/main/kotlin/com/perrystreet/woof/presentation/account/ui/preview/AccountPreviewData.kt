package com.perrystreet.woof.presentation.account.ui.preview

import com.perrystreet.woof.models.dog.DogSize
import com.perrystreet.woof.presentation.account.uimodel.AccountDetailRowUIModel
import com.perrystreet.woof.presentation.account.uimodel.AccountSummaryUIModel
import com.perrystreet.woof.presentation.account.uimodel.AccountUIModel
import com.perrystreet.woof.presentation.account.viewmodel.AccountViewModel

internal object AccountPreviewData {
    fun loaded(): AccountViewModel.State.Loaded = AccountViewModel.State.Loaded(
        account = AccountUIModel(
            name = "Milo",
            photoUrl = "",
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
