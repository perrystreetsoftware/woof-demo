import Models

enum FavoritesPreviewData {
    static func loaded() -> FavoritesViewModel.State {
        .loaded(
            cells: (0..<4).map { index in
                let dog = Dog(id: index, name: "Dog \(index)", photoUrl: "")
                return FavoriteCellUIModel(id: dog.id, name: dog.name, photoUrl: dog.photoUrl, domain: dog)
            }
        )
    }
}
