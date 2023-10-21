package com.gordonfromblumberg.calculator.store

import com.badlogic.gdx.Gdx
import com.gordonfromblumberg.calculator.model.Ingredient
import java.io.DataInputStream
import java.io.DataOutputStream
import java.util.TreeMap

object IngredientStore {
    private const val fileName = "ingredients.data"

    private val INGREDIENTS = TreeMap<String, Ingredient>()

    fun add(ingredient: Ingredient) {
        INGREDIENTS[ingredient.name.lowercase()] = ingredient
    }

    fun load() {
        INGREDIENTS.clear()

        val storeFile = Gdx.files.local(fileName)
        if (!storeFile.exists()) {
            return
        }

        DataInputStream(storeFile.read()).use {
            var count = it.readInt()
            while (count-- > 0) {
                val ingredient = Ingredient()
                ingredient.load(it)
                add(ingredient)
            }
        }
    }

    fun save() {
        val storeFile = Gdx.files.local(fileName)
        DataOutputStream(storeFile.write(false)).use {
            it.writeInt(INGREDIENTS.size)
            INGREDIENTS.values.forEach { i -> i.save(it) }
        }
    }
}