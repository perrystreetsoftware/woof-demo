package com.perrystreet.woof.presentation.favorites.ui.preview

import com.perrystreet.woof.models.dog.Dog
import com.perrystreet.woof.presentation.favorites.uimodel.FavoriteCellUIModel
import com.perrystreet.woof.presentation.favorites.viewmodel.FavoritesViewModel

internal object FavoritesPreviewData {
    fun loaded(): FavoritesViewModel.State.Loaded = FavoritesViewModel.State.Loaded(
        cells = List(4) { index ->
            val dog = Dog(id = index.toLong(), name = "Dog $index", photoUrl = "")
            FavoriteCellUIModel(id = dog.id, name = dog.name, photoUrl = dog.photoUrl, domain = dog)
        },
    )
}
