import Foundation

struct Neighborhood {
    let name: String
    let climate: Climate
}

enum Neighborhoods {
    static let all: [Neighborhood] = [
        Neighborhood(name: "Södermalm, Stockholm", climate: .cold),
        Neighborhood(name: "Nørrebro, Copenhagen", climate: .cold),
        Neighborhood(name: "Plateau, Montreal", climate: .cold),
        Neighborhood(name: "Kensington Market, Toronto", climate: .cold),
        Neighborhood(name: "Wicker Park, Chicago", climate: .cold),
        Neighborhood(name: "Williamsburg, Brooklyn", climate: .temperate),
        Neighborhood(name: "Mission District, San Francisco", climate: .temperate),
        Neighborhood(name: "Capitol Hill, Seattle", climate: .temperate),
        Neighborhood(name: "Shoreditch, London", climate: .temperate),
        Neighborhood(name: "Notting Hill, London", climate: .temperate),
        Neighborhood(name: "Le Marais, Paris", climate: .temperate),
        Neighborhood(name: "Kreuzberg, Berlin", climate: .temperate),
        Neighborhood(name: "Prenzlauer Berg, Berlin", climate: .temperate),
        Neighborhood(name: "Schöneberg, Berlin", climate: .temperate),
        Neighborhood(name: "Jordaan, Amsterdam", climate: .temperate),
        Neighborhood(name: "Shimokitazawa, Tokyo", climate: .temperate),
        Neighborhood(name: "Fitzroy, Melbourne", climate: .temperate),
        Neighborhood(name: "Ponsonby, Auckland", climate: .temperate),
        Neighborhood(name: "Kolonaki, Athens", climate: .warm),
        Neighborhood(name: "Exarcheia, Athens", climate: .warm),
        Neighborhood(name: "Glyfada, Athens", climate: .warm),
        Neighborhood(name: "Silver Lake, Los Angeles", climate: .warm),
        Neighborhood(name: "Trastevere, Rome", climate: .warm),
        Neighborhood(name: "Gràcia, Barcelona", climate: .warm),
        Neighborhood(name: "Malasaña, Madrid", climate: .warm),
        Neighborhood(name: "Chueca, Madrid", climate: .warm),
        Neighborhood(name: "Alfama, Lisbon", climate: .warm),
        Neighborhood(name: "Palermo, Buenos Aires", climate: .warm),
        Neighborhood(name: "Vila Madalena, São Paulo", climate: .warm),
        Neighborhood(name: "Surry Hills, Sydney", climate: .warm),
        Neighborhood(name: "Zona Rosa, Mexico City", climate: .warm),
    ]

    static func matching(_ climates: Set<Climate>) -> [Neighborhood] {
        all.filter { climates.contains($0.climate) }
    }
}
