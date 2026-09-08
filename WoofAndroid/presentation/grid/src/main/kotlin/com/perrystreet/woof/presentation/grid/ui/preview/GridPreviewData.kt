package com.perrystreet.woof.presentation.grid.ui.preview

import com.perrystreet.woof.models.dog.Dog
import com.perrystreet.woof.presentation.grid.uimodel.DogCellUIModel
import com.perrystreet.woof.presentation.grid.viewmodel.GridViewModel

internal object GridPreviewData {
    fun loaded(): GridViewModel.State.Loaded = GridViewModel.State.Loaded(
        cells = List(12) { index ->
            val dog = Dog(id = index.toLong(), name = "Dog $index", photoUrl = "")
            DogCellUIModel(index = index, id = dog.id, name = dog.name, photoUrl = dog.photoUrl, domain = dog)
        },
        isLoadingMore = true,
        hasMore = true,
    )
}
