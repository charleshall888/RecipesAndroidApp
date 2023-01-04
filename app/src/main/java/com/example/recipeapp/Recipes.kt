package com.example.recipeapp

class Recipes {

    val recipes = arrayOf(
        "recipe" to Recipe(
            id = 0,
            name = "Chocolate Milkshake",
            description = "2 pumps of chocolate syrup",
            tags = arrayOf("dessert", "milkshake", "chocolate")
        ),
        "recipe" to Recipe(
            id = 1,
            name = "Strawberry Milkshake",
            description = "2 pumps of strawberry jam",
            tags = arrayOf("dessert", "milkshake", "strawberry")
        )
    );

}
