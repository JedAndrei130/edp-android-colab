package com.example.myapplication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DishViewModel : ViewModel() {

    private val _dishes = MutableStateFlow(
        listOf(
            Dish(id = 1, name = "Chicken Adobo"),
            Dish(id = 2, name = "Sinigang na Baboy")
        )
    )
    val dishes: StateFlow<List<Dish>> = _dishes.asStateFlow()

    private var nextId = 100

    // ---------------- DISH CRUD ----------------
    fun addDish(name: String) {
        if (name.isBlank()) return
        val newDish = Dish(id = nextId++, name = name.trim())
        _dishes.value = _dishes.value + newDish
    }

    fun getDish(dishId: Int): Dish? {
        return _dishes.value.find { it.id == dishId }
    }

    // TODO 1
    fun updateDish(dishId: Int, newName: String) {
        if (newName.isBlank()) return
        _dishes.value = _dishes.value.map { dish ->
            if (dish.id == dishId) dish.copy(name = newName.trim()) else dish
        }
    }

    // TODO 2
    fun deleteDish(dishId: Int) {
        _dishes.value = _dishes.value.filter { it.id != dishId }
    }

    // ---------------- RECIPE CRUD ----------------
    // TODO 3
    fun addRecipe(dishId: Int, text: String) {
        if (text.isBlank()) return
        val newRecipe = Recipe(id = nextId++, text = text.trim())
        _dishes.value = _dishes.value.map { dish ->
            if (dish.id == dishId) {
                dish.copy(recipes = dish.recipes + newRecipe)
            } else {
                dish
            }
        }
    }

    // TODO 4
    fun updateRecipe(dishId: Int, recipeId: Int, newText: String) {
        if (newText.isBlank()) return
        _dishes.value = _dishes.value.map { dish ->
            if (dish.id == dishId) {
                dish.copy(
                    recipes = dish.recipes.map { recipe ->
                        if (recipe.id == recipeId) recipe.copy(text = newText.trim()) else recipe
                    }
                )
            } else {
                dish
            }
        }
    }

    // TODO 5
    fun deleteRecipe(dishId: Int, recipeId: Int) {
        _dishes.value = _dishes.value.map { dish ->
            if (dish.id == dishId) {
                dish.copy(recipes = dish.recipes.filter { it.id != recipeId })
            } else {
                dish
            }
        }
    }
}