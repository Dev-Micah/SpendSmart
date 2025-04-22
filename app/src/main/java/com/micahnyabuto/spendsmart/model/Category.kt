package com.micahnyabuto.spendsmart.model

import androidx.compose.ui.graphics.Color

data class Category(
    val name: String,
    val color: String
){
    override fun toString(): String {
        return name
    }
}
