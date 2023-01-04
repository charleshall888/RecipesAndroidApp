package com.example.recipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recipeapp.ui.theme.RecipeAppTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    private var recipeId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeAppTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    color = MaterialTheme.colors.background
                ) {
                    val recipes = Recipes();
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController, startDestination = "recipes"
                    ) {

                        composable("recipes") {
                            RecipesScreen(
                                navController,
                                recipes = recipes
                            )
                        }
                        composable("recipe") {
                            RecipeScreen(
                                navController = navController, recipe = recipes.recipes[recipeId].second
                            )
                        }
                    }
                }
            }
        }

    }


    @Composable
    fun RecipeScreen(navController: NavController, recipe: Recipe) {
        Column() {
            Box() {
                Image(
                    painter = painterResource(R.drawable.chickenwing),
                    contentDescription = "Recipe Picture",
                    modifier = Modifier
                        // Clip image to be shaped as a circle
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 12.dp, topEnd = 0.dp, bottomEnd = 12.dp
                            )
                        )
                        .border(
                            1.5.dp, MaterialTheme.colors.primaryVariant, RoundedCornerShape(
                                bottomStart = 12.dp, topEnd = 0.dp, bottomEnd = 12.dp
                            )
                        )
                )
                Button(onClick = { navController.navigate("recipes") }) {
                    Icon(
                        Icons.Rounded.ArrowBack,
                        contentDescription = "Back to Recipes",
                        tint = MaterialTheme.colors.primaryVariant,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
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
                            text = "+$tag", color = MaterialTheme.colors.secondaryVariant
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            var description by remember { mutableStateOf(recipe.description) }
            TextField(
                value = description,
                onValueChange = {  description = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
            )
        }

    }

    @Composable
    fun RecipesScreen(navController: NavController, recipes: Recipes) {
        Column {
            for (r in recipes.recipes) {
                val recipe = r.second
                RecipeCard(
                    navController, Recipe(
                        id = recipe.id, name = recipe.name, description = recipe.description, tags = recipe.tags
                    )
                )
                Divider(thickness = 5.dp)
            }
        }
    }


    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun RecipeCard(navController: NavController, recipe: Recipe) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            onClick = {this.recipeId = recipe.id;
                navController.navigate("recipe")}

        )
        {
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
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween
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
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
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
