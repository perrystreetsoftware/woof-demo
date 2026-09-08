package com.perrystreet.woof.datasource.dogs.fixtures

import com.perrystreet.woof.dto.dog.DogDTO
import com.perrystreet.woof.dto.dog.DogProfileDTO
import com.perrystreet.woof.dto.dog.DogsPageDTO
import kotlin.random.Random

object DogFixtures {
    const val TotalDogs = 1_000

    private val profiles: List<DogProfileDTO> by lazy { generateProfiles() }

    fun page(offset: Int, limit: Int): DogsPageDTO {
        val safeOffset = offset.coerceIn(0, TotalDogs)
        val results = profiles
            .drop(safeOffset)
            .take(limit)
            .map { it.dog }
        return DogsPageDTO(results = results, offset = safeOffset, total = TotalDogs)
    }

    fun profile(dogId: Long): DogProfileDTO =
        profiles.firstOrNull { it.dog.id == dogId } ?: throw NoSuchElementException("No dog with id $dogId")

    fun account(): DogProfileDTO = DogProfileDTO(
        dog = DogDTO(id = AccountDogId, name = "Milo", photoUrl = photoUrl(AccountBreed)),
        breed = AccountBreed.name,
        ageInYears = 3,
        size = AccountBreed.size,
        neighborhood = "Schöneberg, Berlin",
        personality = listOf("Frisbee pro", "Early riser", "Squirrel chaser", "Velcro dog"),
        favoriteActivity = "Agility courses",
        bio = "Herds tennis balls for a living. Weekends are for agility courses, long hikes, and stealing socks.",
    )

    private fun generateProfiles(): List<DogProfileDTO> {
        val random = Random(seed = 20_260_902)
        return (1..TotalDogs).map { index ->
            val name = DogNames.all[random.nextInt(DogNames.all.size)]
            val activity = FavoriteActivities[random.nextInt(FavoriteActivities.size)]
            val breed = DogBreeds.all[random.nextInt(DogBreeds.all.size)]
            val neighborhoods = Neighborhoods.matching(breed.climates)
            DogProfileDTO(
                dog = DogDTO(
                    id = index.toLong(),
                    name = name,
                    photoUrl = photoUrl(breed),
                ),
                breed = breed.name,
                ageInYears = random.nextInt(1, 15),
                size = breed.size,
                neighborhood = neighborhoods[random.nextInt(neighborhoods.size)].name,
                personality = PersonalityTraits.shuffled(random).take(random.nextInt(2, 5)),
                favoriteActivity = activity,
                bio = BioTemplates[random.nextInt(BioTemplates.size)]
                    .replace("{name}", name)
                    .replace("{activity}", activity.lowercase())
                    .replace("{breed}", breed.name.lowercase()),
            )
        }
    }

    private fun photoUrl(breed: DogBreed) = "file:///android_asset/dogs/${breed.photoSlug}_01.jpg"

    private const val AccountDogId = 0L
    private val AccountBreed = DogBreeds.named("Border Collie")

    private val PersonalityTraits = listOf(
        "Playful", "Cuddly", "Zoomies expert", "Gentle giant", "Snack enthusiast", "Ball obsessed",
        "Water lover", "Couch potato", "Early riser", "Social butterfly", "Shy at first", "Goofy",
        "Loyal", "Curious", "Fearless", "Velcro dog", "Squirrel chaser", "Talkative", "Chill",
        "Adventurous", "Tug-of-war champ", "Belly rub addict", "Puddle jumper", "Stick collector",
        "Nap professional", "Treat negotiator", "Sunbather", "Mud magnet", "Frisbee pro", "Bed hog",
    )

    private val FavoriteActivities = listOf(
        "Fetch at the park", "Swimming at the beach", "Long hikes", "Agility courses", "Beach runs",
        "Puzzle toys", "Digging holes", "Chasing tennis balls", "Dog park playdates", "Car rides",
        "Snow zoomies", "Frisbee", "Sniffing every tree", "Cafe patio naps", "Trail running",
        "Paddleboarding", "Tug of war", "Hide and seek", "Learning tricks", "Backyard sunbathing",
        "Morning jogs", "Lake fetch", "Flyball", "Nose work", "Dock diving", "Sunset walks",
        "Meeting new pups", "Rolling in the grass", "Kayak trips", "Farmers market strolls",
    )

    private val BioTemplates = listOf(
        "Loves {activity}, tennis balls, and stealing snacks when nobody is looking.",
        "{name} here. Big fan of {activity} and even bigger fan of belly rubs.",
        "Professional good dog. Will trade tricks for treats. Ask me about {activity}.",
        "Part {breed}, part vacuum cleaner. Looking for playdate buddies who enjoy {activity}.",
        "Energy level: 11/10. Favorite pastime: {activity}. Weakness: squirrels.",
        "Sweet, a little clumsy, and always up for {activity}. Bring snacks.",
        "{name} has never met a puddle worth avoiding. Down for {activity} any day of the week.",
        "Certified snuggler with a black belt in {activity}. Cats welcome, birds not so much.",
        "New to the neighborhood and looking for friends who like {activity} as much as I do.",
        "Will sit, stay, and roll over for cheese. Weekend plans usually involve {activity}.",
        "Gentle {breed} who loves long walks, short naps, and {activity} with the pack.",
        "Half of my day is {activity}. The other half is dreaming about {activity}.",
        "Fetch is life. Also open to {activity}, car rides, and any sunny patch of grass.",
        "Small but mighty. Big personality, bigger ears. Let's go do some {activity}.",
        "Retired couch potato making a comeback through {activity}. Slow and steady.",
        "Friendly with every dog, human, and mail carrier. Ask me to join you for {activity}.",
        "{name} is the mayor of the local dog park and hosts {activity} every Saturday.",
        "Stick collector, puddle expert, {activity} enthusiast. Zero chill, all love.",
        "Loves {activity} but will absolutely nap through the second half of it.",
        "Rescue pup with a heart of gold. Learning to love {activity} one day at a time.",
    )
}
