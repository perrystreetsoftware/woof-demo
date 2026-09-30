import Models

enum BrowsePreviewData {
    static func loaded() -> BrowseViewModel.State {
        .loaded(
            BrowseViewModel.State.Loaded(
                cells: (0..<12).map { index in
                    let dog = Dog(id: index, name: "Dog \(index)", photoUrl: "")
                    return DogCellUIModel(index: index, id: dog.id, name: dog.name, photoUrl: dog.photoUrl, domain: dog)
                },
                isLoadingMore: true,
                hasMore: true
            )
        )
    }
}
