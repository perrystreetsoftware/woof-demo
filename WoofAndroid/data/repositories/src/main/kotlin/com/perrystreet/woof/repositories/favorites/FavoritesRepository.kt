package com.perrystreet.woof.repositories.favorites

import com.perrystreet.woof.datasource.favorites.IFavoritesDataSource
import com.perrystreet.woof.models.dog.Dog
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.subjects.BehaviorSubject
import org.koin.core.annotation.Single

@Single
class FavoritesRepository(
    private val dataSource: IFavoritesDataSource,
) {
    private val disposables = CompositeDisposable()
    private val favorites: BehaviorSubject<Set<Long>> = BehaviorSubject.createDefault(emptySet())

    val favoriteDogIds: Observable<Set<Long>> = favorites

    fun addFavorite(dog: Dog): Completable {
        favorites.onNext(favorites.value!! + dog.id)
        val stream = dataSource.addFavorite(dog.id)
            .doOnError { favorites.onNext(favorites.value!! - dog.id) }
            .cache()
        disposables += stream.subscribe({}, {})
        return stream
    }

    fun removeFavorite(dog: Dog): Completable {
        favorites.onNext(favorites.value!! - dog.id)
        val stream = dataSource.removeFavorite(dog.id)
            .doOnError { favorites.onNext(favorites.value!! + dog.id) }
            .cache()
        disposables += stream.subscribe({}, {})
        return stream
    }
}
