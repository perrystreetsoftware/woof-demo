import DTO
import Foundation

struct DogBreed {
    let name: String
    let photoSlug: String
    let size: String
    let climates: Set<Climate>
}

enum DogBreeds {
    private static let anyClimate = Set(Climate.allCases)
    private static let coldClimate: Set<Climate> = [.cold]
    private static let coolClimate: Set<Climate> = [.cold, .temperate]
    private static let mildClimate: Set<Climate> = [.temperate, .warm]

    static let all: [DogBreed] = [
        DogBreed(name: "Golden Retriever", photoSlug: "golden_retriever", size: DogSizeDTO.large, climates: anyClimate),
        DogBreed(name: "Labrador Retriever", photoSlug: "labrador_retriever", size: DogSizeDTO.large, climates: mildClimate),
        DogBreed(name: "French Bulldog", photoSlug: "french_bulldog", size: DogSizeDTO.small, climates: mildClimate),
        DogBreed(name: "German Shepherd", photoSlug: "german_shepherd", size: DogSizeDTO.large, climates: anyClimate),
        DogBreed(name: "Poodle", photoSlug: "poodle", size: DogSizeDTO.medium, climates: anyClimate),
        DogBreed(name: "Beagle", photoSlug: "beagle", size: DogSizeDTO.medium, climates: anyClimate),
        DogBreed(name: "Dachshund", photoSlug: "dachshund", size: DogSizeDTO.small, climates: anyClimate),
        DogBreed(name: "Corgi", photoSlug: "corgi", size: DogSizeDTO.small, climates: anyClimate),
        DogBreed(name: "Border Collie", photoSlug: "border_collie", size: DogSizeDTO.medium, climates: anyClimate),
        DogBreed(name: "Siberian Husky", photoSlug: "siberian_husky", size: DogSizeDTO.large, climates: coldClimate),
        DogBreed(name: "Boxer", photoSlug: "boxer", size: DogSizeDTO.large, climates: anyClimate),
        DogBreed(name: "Great Dane", photoSlug: "great_dane", size: DogSizeDTO.large, climates: anyClimate),
        DogBreed(name: "Shiba Inu", photoSlug: "shiba_inu", size: DogSizeDTO.medium, climates: anyClimate),
        DogBreed(name: "Australian Shepherd", photoSlug: "australian_shepherd", size: DogSizeDTO.medium, climates: coolClimate),
        DogBreed(name: "Bernese Mountain Dog", photoSlug: "bernese_mountain_dog", size: DogSizeDTO.large, climates: coldClimate),
        DogBreed(name: "Jack Russell Terrier", photoSlug: "jack_russell_terrier", size: DogSizeDTO.small, climates: anyClimate),
        DogBreed(name: "Pug", photoSlug: "pug", size: DogSizeDTO.small, climates: mildClimate),
        DogBreed(name: "Samoyed", photoSlug: "samoyed", size: DogSizeDTO.large, climates: coldClimate),
        DogBreed(name: "Dalmatian", photoSlug: "dalmatian", size: DogSizeDTO.large, climates: anyClimate),
        DogBreed(name: "Chihuahua", photoSlug: "chihuahua", size: DogSizeDTO.small, climates: mildClimate),
    ]

    static func named(_ name: String) -> DogBreed {
        all.first { $0.name == name }!
    }
}
