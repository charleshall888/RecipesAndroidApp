package com.example.recipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recipeapp.ui.theme.RecipeAppTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "recipes"
                    ) {
                        composable("recipes") {
                            RecipesScreen(
                                onNavigateToRecipe = { navController.navigate("recipe") },
                                recipes = arrayOf<Recipe>(
                                    Recipe(
                                        name = "2 Wings",
                                        body = "Hey, take a look at Jetpack Compose, it's great!",
                                        tags = arrayOf<String>("Chicken", "Apps", "Party")
                                    )
                                )
                            )
                        }
                        composable("recipe") { RecipeScreen(navController = navController) }
                    }
                }
            }
        }

    }

    data class Recipe(val name: String, val body: String, val tags: Array<String>)

    @Composable
    fun RecipeScreen(navController: NavController) {
        Column() {
            
        }
        Text(text = "Hello!")
        TextField(value = "test", onValueChange = {println("test")})
    }

    @Composable
    fun RecipesScreen(onNavigateToRecipe: () -> Unit, recipes: Array<Recipe>) {
        Column() {
            for (recipe in recipes) {
                RecipeCard(
                    onNavigateToRecipe = onNavigateToRecipe,
                    Recipe(
                        name = recipe.name,
                        body = recipe.body,
                        tags = recipe.tags
                    )
                )
            }
        }
    }


    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun RecipeCard(onNavigateToRecipe: () -> Unit, recipe: Recipe) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            onClick = onNavigateToRecipe

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Image(
                    painter = painterResource(R.drawable.chickenwing),
                    contentDescription = "Recipe Picture",
                    modifier = Modifier
                        // Clip image to be shaped as a circle
                        .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
                        .border(
                            1.5.dp,
                            MaterialTheme.colors.primaryVariant,
                            RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp)
                        )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(), horizontalArrangement = Arrangement.SpaceBetween
                ) {


                    Column(verticalArrangement = Arrangement.SpaceBetween) {
                        Text(
                            text = recipe.name,
                            style = MaterialTheme.typography.h5,
                            color = MaterialTheme.colors.primaryVariant
                        )
                        Spacer(modifier = Modifier.height(4.dp))

                        Row() {
                            for (tag in recipe.tags) {
                                Card() {
                                    Text(
                                        text = "+$tag",
                                        color = MaterialTheme.colors.secondaryVariant
                                    )
                                }
                                Spacer(modifier = Modifier.width(4.dp))
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(), verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Rounded.ArrowForward,
                            contentDescription = "View Recipe",
                            tint = MaterialTheme.colors.primaryVariant,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                }
            }
        }
    }
}
