package com.perrystreet.woof.datasource.dogs.fixtures

internal data class Neighborhood(
    val name: String,
    val climate: Climate,
)

internal object Neighborhoods {
    val all: List<Neighborhood> = listOf(
        Neighborhood("Södermalm, Stockholm", Climate.Cold),
        Neighborhood("Nørrebro, Copenhagen", Climate.Cold),
        Neighborhood("Plateau, Montreal", Climate.Cold),
        Neighborhood("Kensington Market, Toronto", Climate.Cold),
        Neighborhood("Wicker Park, Chicago", Climate.Cold),
        Neighborhood("Williamsburg, Brooklyn", Climate.Temperate),
        Neighborhood("Mission District, San Francisco", Climate.Temperate),
        Neighborhood("Capitol Hill, Seattle", Climate.Temperate),
        Neighborhood("Shoreditch, London", Climate.Temperate),
        Neighborhood("Notting Hill, London", Climate.Temperate),
        Neighborhood("Le Marais, Paris", Climate.Temperate),
        Neighborhood("Kreuzberg, Berlin", Climate.Temperate),
        Neighborhood("Prenzlauer Berg, Berlin", Climate.Temperate),
        Neighborhood("Schöneberg, Berlin", Climate.Temperate),
        Neighborhood("Jordaan, Amsterdam", Climate.Temperate),
        Neighborhood("Shimokitazawa, Tokyo", Climate.Temperate),
        Neighborhood("Fitzroy, Melbourne", Climate.Temperate),
        Neighborhood("Ponsonby, Auckland", Climate.Temperate),
        Neighborhood("Kolonaki, Athens", Climate.Warm),
        Neighborhood("Exarcheia, Athens", Climate.Warm),
        Neighborhood("Glyfada, Athens", Climate.Warm),
        Neighborhood("Silver Lake, Los Angeles", Climate.Warm),
        Neighborhood("Trastevere, Rome", Climate.Warm),
        Neighborhood("Gràcia, Barcelona", Climate.Warm),
        Neighborhood("Malasaña, Madrid", Climate.Warm),
        Neighborhood("Chueca, Madrid", Climate.Warm),
        Neighborhood("Alfama, Lisbon", Climate.Warm),
        Neighborhood("Palermo, Buenos Aires", Climate.Warm),
        Neighborhood("Vila Madalena, São Paulo", Climate.Warm),
        Neighborhood("Surry Hills, Sydney", Climate.Warm),
        Neighborhood("Zona Rosa, Mexico City", Climate.Warm),
    )

    fun matching(climates: Set<Climate>): List<Neighborhood> = all.filter { it.climate in climates }
}
