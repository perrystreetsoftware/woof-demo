import DTO
import Foundation

public enum DogFixtures {
    public static let totalDogs = 1000

    private static let profiles: [DogProfileDTO] = generateProfiles()

    public static func page(offset: Int, limit: Int) -> DogsPageDTO {
        let safeOffset = min(max(offset, 0), totalDogs)
        let results = profiles
            .dropFirst(safeOffset)
            .prefix(limit)
            .map { $0.dog }
        return DogsPageDTO(results: Array(results), offset: safeOffset, total: totalDogs)
    }

    public static func profile(dogId: Int) -> DogProfileDTO? {
        profiles.first { $0.dog.id == dogId }
    }

    public static func account() -> DogProfileDTO {
        DogProfileDTO(
            dog: DogDTO(id: accountDogId, name: "Milo", photoUrl: photoUrl(accountBreed)),
            breed: accountBreed.name,
            ageInYears: 3,
            size: accountBreed.size,
            neighborhood: "Schöneberg, Berlin",
            personality: ["Frisbee pro", "Early riser", "Squirrel chaser", "Velcro dog"],
            favoriteActivity: "Agility courses",
            bio: "Herds tennis balls for a living. Weekends are for agility courses, long hikes, and stealing socks."
        )
    }

    private static func generateProfiles() -> [DogProfileDTO] {
        var random = SeededRandom(seed: 20_260_902)
        return (1...totalDogs).map { index in
            let name = DogNames.all[random.nextInt(DogNames.all.count)]
            let activity = favoriteActivities[random.nextInt(favoriteActivities.count)]
            let breed = DogBreeds.all[random.nextInt(DogBreeds.all.count)]
            let neighborhoods = Neighborhoods.matching(breed.climates)
            return DogProfileDTO(
                dog: DogDTO(
                    id: index,
                    name: name,
                    photoUrl: photoUrl(breed)
                ),
                breed: breed.name,
                ageInYears: random.nextInt(1, 15),
                size: breed.size,
                neighborhood: neighborhoods[random.nextInt(neighborhoods.count)].name,
                personality: Array(personalityTraits.shuffled(using: &random).prefix(random.nextInt(2, 5))),
                favoriteActivity: activity,
                bio: bioTemplates[random.nextInt(bioTemplates.count)]
                    .replacingOccurrences(of: "{name}", with: name)
                    .replacingOccurrences(of: "{activity}", with: activity.lowercased())
                    .replacingOccurrences(of: "{breed}", with: breed.name.lowercased())
            )
        }
    }

    private static func photoUrl(_ breed: DogBreed) -> String {
        "woof://dogs/\(breed.photoSlug)_01.jpg"
    }

    private static let accountDogId = 0
    private static let accountBreed = DogBreeds.named("Border Collie")

    private static let personalityTraits = [
        "Playful", "Cuddly", "Zoomies expert", "Gentle giant", "Snack enthusiast", "Ball obsessed",
        "Water lover", "Couch potato", "Early riser", "Social butterfly", "Shy at first", "Goofy",
        "Loyal", "Curious", "Fearless", "Velcro dog", "Squirrel chaser", "Talkative", "Chill",
        "Adventurous", "Tug-of-war champ", "Belly rub addict", "Puddle jumper", "Stick collector",
        "Nap professional", "Treat negotiator", "Sunbather", "Mud magnet", "Frisbee pro", "Bed hog",
    ]

    private static let favoriteActivities = [
        "Fetch at the park", "Swimming at the beach", "Long hikes", "Agility courses", "Beach runs",
        "Puzzle toys", "Digging holes", "Chasing tennis balls", "Dog park playdates", "Car rides",
        "Snow zoomies", "Frisbee", "Sniffing every tree", "Cafe patio naps", "Trail running",
        "Paddleboarding", "Tug of war", "Hide and seek", "Learning tricks", "Backyard sunbathing",
        "Morning jogs", "Lake fetch", "Flyball", "Nose work", "Dock diving", "Sunset walks",
        "Meeting new pups", "Rolling in the grass", "Kayak trips", "Farmers market strolls",
    ]

    private static let bioTemplates = [
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
    ]
}
