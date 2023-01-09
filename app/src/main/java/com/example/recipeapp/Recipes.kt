package com.example.recipeapp

import android.net.Uri

class Recipes {

    val recipes = arrayListOf(
        Recipe(
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
        Recipe(
            id = 1,
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
        Recipe(
            id = 2,
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
        Recipe(
            id = 3,
            name = "Vanilla Milkshake",
            description = "Milkshake base to red line" +
                    "\nIcedream to top" +
                    "\nLid on" +
                    "\nBlend" +
                    "\nWhipped Cream" +
                    "\nCherry on Top",
            imageUri = Uri.parse("android.resource://com.example.recipeapp/drawable/vanillamilkshake")
        ),
        Recipe(
            id = 4,
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
