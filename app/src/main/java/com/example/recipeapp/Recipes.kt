package com.example.recipeapp

import android.net.Uri

class Recipes {

    val recipes = arrayOf(
        "recipe" to Recipe(
            id = 0,
            name = "Chocolate Milkshake",
            description = "2 pumps of chocolate syrup",
            tags = arrayOf("dessert", "milkshake", "chocolate"),
            imageUri = Uri.parse("android.resource://com.example.recipeapp/drawable/chickenwing")
        ),
        "recipe" to Recipe(
            id = 1,
            name = "Strawberry Milkshake",
            description = "2 pumps of strawberry jam",
            tags = arrayOf("dessert", "milkshake", "strawberry"),
            imageUri = Uri.parse("android.resource://com.example.recipeapp/drawable/chickenwing")
        )
    );

}
