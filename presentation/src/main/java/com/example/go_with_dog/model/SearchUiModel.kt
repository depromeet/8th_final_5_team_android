package com.example.go_with_dog.model

import android.graphics.drawable.Drawable

data class SearchUiModel (
    val id: Long,
    val name: String,
    val dog: String,
    val old: String,
    val gender: String,
    val category: String,
    val background: Drawable
)