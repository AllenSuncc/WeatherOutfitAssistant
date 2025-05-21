package com.example.myapplication.util.weather

fun getClothingSuggestion(temp: Double, description: String): String {
    val weather = description.lowercase()

    return when {
        temp >= 30 -> "Wear light clothing like T-shirts and shorts. Avoid outdoor activities and apply sunscreen."
        temp in 25.0..29.9 -> "T-shirts, skirts or thin pants are recommended. Consider wearing sunglasses and a hat."
        temp in 18.0..24.9 -> "A long-sleeve shirt or a light jacket is suitable for mild weather."
        temp in 10.0..17.9 -> "Sweaters, hoodies, or windbreakers are recommended for cooler weather."
        temp in 0.0..9.9 -> "Wear thick coats, scarves, and consider thermal underlayers."
        temp < 0 -> "Very cold! Wear down jackets, gloves, and a warm hat."

        // Fallback for special weather cases
        else -> when {
            "rain" in weather -> "Carry an umbrella and wear waterproof shoes or a raincoat."
            "snow" in weather -> "Dress warmly and wear non-slip shoes in snowy conditions."
            "thunderstorm" in weather -> "Avoid going outdoors during thunderstorms. Wear waterproof gear if needed."
            else -> "Dress appropriately for the current weather."
        }
    }
}