package com.example.recipeapp

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.ui.Modifier
import com.example.recipeapp.ui.theme.RecipeAppTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
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
                                navController = navController,
                                recipe = recipes.recipes[recipeId].second
                            )
                        }
                    }
                }
            }
        }
    }


    @Composable
    fun RecipeScreen(navController: NavController, recipe: Recipe) {
        var imageUri by remember { mutableStateOf<Uri?>(recipe.imageUri) }
        val context = LocalContext.current
        var bitmap: Bitmap

        val launcher =
            rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
                imageUri = uri
                if (uri != null) {
                    recipe.imageUri = uri
                }
            }
        Column() {
            Box() {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    imageUri?.let {
                        if (Build.VERSION.SDK_INT < 28) {
                            bitmap = MediaStore.Images
                                .Media.getBitmap(context.contentResolver, it)
                        } else {
                            val source = ImageDecoder.createSource(context.contentResolver, it)
                            bitmap = ImageDecoder.decodeBitmap(source)
                        }
                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = "Recipe Picture",
                            modifier = Modifier
                                // Clip image to be shaped as a circle
                                .clip(
                                    RoundedCornerShape(
                                        bottomStart = 12.dp,
                                        topEnd = 0.dp,
                                        bottomEnd = 12.dp
                                    )
                                )
                                .border(
                                    1.5.dp,
                                    MaterialTheme.colors.primaryVariant,
                                    RoundedCornerShape(
                                        bottomStart = 12.dp,
                                        topEnd = 0.dp,
                                        bottomEnd = 12.dp
                                    )
                                )
                                .height(300.dp)
                                .clickable { launcher.launch("image/*") }
                        )
                    }
                }
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
            var name by remember { mutableStateOf(recipe.name) }
            TextField(
                value = name,
                onValueChange = {
                    name = it;
                    recipe.name = name
                },
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = MaterialTheme.typography.h5
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
                onValueChange = {
                    description = it;
                    recipe.description = description
                },
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
                        id = recipe.id,
                        name = recipe.name,
                        description = recipe.description,
                        tags = recipe.tags,
                        imageUri = recipe.imageUri
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
            onClick = {
                this.recipeId = recipe.id;
                navController.navigate("recipe")
            }

        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                val context = LocalContext.current
                var bitmap: Bitmap
                if (Build.VERSION.SDK_INT < 28) {
                    bitmap = MediaStore.Images
                        .Media.getBitmap(context.contentResolver, recipe.imageUri)
                } else {
                    val source = ImageDecoder.createSource(context.contentResolver, recipe.imageUri)
                    bitmap = ImageDecoder.decodeBitmap(source)
                }
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "Recipe Picture",
                    modifier = Modifier
                        // Clip image to be shaped as a circle
                        .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
                        .border(
                            1.5.dp,
                            MaterialTheme.colors.primaryVariant,
                            RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp)
                        )
                        .width(100.dp)
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
