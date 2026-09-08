package com.perrystreet.woof.datasource.dogs.fixtures

import com.perrystreet.woof.dto.dog.DogSizeDTO

internal data class DogBreed(
    val name: String,
    val photoSlug: String,
    val size: String,
    val climates: Set<Climate>,
)

internal object DogBreeds {
    private val AnyClimate = Climate.entries.toSet()
    private val ColdClimate = setOf(Climate.Cold)
    private val CoolClimate = setOf(Climate.Cold, Climate.Temperate)
    private val MildClimate = setOf(Climate.Temperate, Climate.Warm)

    val all: List<DogBreed> = listOf(
        DogBreed("Golden Retriever", "golden_retriever", DogSizeDTO.Large, AnyClimate),
        DogBreed("Labrador Retriever", "labrador_retriever", DogSizeDTO.Large, MildClimate),
        DogBreed("French Bulldog", "french_bulldog", DogSizeDTO.Small, MildClimate),
        DogBreed("German Shepherd", "german_shepherd", DogSizeDTO.Large, AnyClimate),
        DogBreed("Poodle", "poodle", DogSizeDTO.Medium, AnyClimate),
        DogBreed("Beagle", "beagle", DogSizeDTO.Medium, AnyClimate),
        DogBreed("Dachshund", "dachshund", DogSizeDTO.Small, AnyClimate),
        DogBreed("Corgi", "corgi", DogSizeDTO.Small, AnyClimate),
        DogBreed("Border Collie", "border_collie", DogSizeDTO.Medium, AnyClimate),
        DogBreed("Siberian Husky", "siberian_husky", DogSizeDTO.Large, ColdClimate),
        DogBreed("Boxer", "boxer", DogSizeDTO.Large, AnyClimate),
        DogBreed("Great Dane", "great_dane", DogSizeDTO.Large, AnyClimate),
        DogBreed("Shiba Inu", "shiba_inu", DogSizeDTO.Medium, AnyClimate),
        DogBreed("Australian Shepherd", "australian_shepherd", DogSizeDTO.Medium, CoolClimate),
        DogBreed("Bernese Mountain Dog", "bernese_mountain_dog", DogSizeDTO.Large, ColdClimate),
        DogBreed("Jack Russell Terrier", "jack_russell_terrier", DogSizeDTO.Small, AnyClimate),
        DogBreed("Pug", "pug", DogSizeDTO.Small, MildClimate),
        DogBreed("Samoyed", "samoyed", DogSizeDTO.Large, ColdClimate),
        DogBreed("Dalmatian", "dalmatian", DogSizeDTO.Large, AnyClimate),
        DogBreed("Chihuahua", "chihuahua", DogSizeDTO.Small, MildClimate),
    )

    fun named(name: String): DogBreed = all.first { it.name == name }
}
