package com.example.recipeapp

import android.net.Uri

class Recipes {

    val recipes = arrayOf(
        "recipe" to Recipe(
            id = 0,
            name = "Chocolate Milkshake",
            description = "2 pumps of chocolate syrup" +
                    "\nMilkshake base to red line" +
                    "\nIcedream to top" +
                    "\nLid on" +
                    "\nBlend" +
                    "\nWhipped Cream" +
                    "\nCherry on Top",
            imageUri = Uri.parse("android.resource://com.example.recipeapp/drawable/chocolatemilkshake")
        ),
        "recipe" to Recipe(
            id = 0,
            name = "Strawberry Milkshake",
            description = "2 pumps of strawberry jam" +
                    "\nMilkshake base to red line" +
                    "\nIcedream to top" +
                    "\nLid on" +
                    "\nBlend" +
                    "\nWhipped Cream" +
                    "\nCherry on Top",
            imageUri = Uri.parse("android.resource://com.example.recipeapp/drawable/strawberrymilkshake")
        ),
        "recipe" to Recipe(
            id = 0,
            name = "Cookie&Cream Milkshake",
            description = "2 scoops of cookie crumbles" +
                    "\nMilkshake base to red line" +
                    "\nIcedream to top" +
                    "\nLid on" +
                    "\nBlend" +
                    "\nWhipped Cream" +
                    "\nCherry on Top",
            imageUri = Uri.parse("android.resource://com.example.recipeapp/drawable/ccmilkshake")
        ),
        "recipe" to Recipe(
            id = 0,
            name = "Vanilla Milkshake",
            description = "Milkshake base to red line" +
                    "\nIcedream to top" +
                    "\nLid on" +
                    "\nBlend" +
                    "\nWhipped Cream" +
                    "\nCherry on Top",
            imageUri = Uri.parse("android.resource://com.example.recipeapp/drawable/vanillamilkshake")
        ),
        "recipe" to Recipe(
            id = 0,
            name = "Cookie Crumble Shake",
            description = "1 Chocolate Chip cookie crumbled finely" +
                    "\nMilkshake base to red line" +
                    "\nIcedream to top" +
                    "\nLid on" +
                    "\nBlend" +
                    "\nWhipped Cream" +
                    "\nCherry on Top",
            imageUri = Uri.parse("android.resource://com.example.recipeapp/drawable/cookieshake")
        ),
    );

}
