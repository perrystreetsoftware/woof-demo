package com.perrystreet.woof.presentation.browse.ui.preview

import com.perrystreet.woof.models.dog.Dog
import com.perrystreet.woof.presentation.browse.uimodel.DogCellUIModel
import com.perrystreet.woof.presentation.browse.viewmodel.BrowseViewModel

internal object BrowsePreviewData {
    fun loaded(): BrowseViewModel.State.Loaded = BrowseViewModel.State.Loaded(
        cells = List(12) { index ->
            val dog = Dog(id = index.toLong(), name = "Dog $index", photoUrl = "")
            DogCellUIModel(index = index, id = dog.id, name = dog.name, photoUrl = dog.photoUrl, domain = dog)
        },
        isLoadingMore = true,
        hasMore = true,
    )
}
