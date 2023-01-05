package com.example.recipeapp

import android.media.Image
import android.net.Uri

class Recipe(val id: Int, var name: String, var description: String, var tags: Array<String>, var imageUri: Uri)
