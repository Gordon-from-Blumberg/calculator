package com.gordonfromblumberg.calculator.store

import com.badlogic.gdx.Gdx
import com.gordonfromblumberg.calculator.model.Ingredient
import java.io.DataInputStream
import java.io.DataOutputStream

object IngredientStore {
    private const val fileName = "ingredients.data"

    val INGREDIENTS = mutableListOf<Ingredient>()

    fun load() {
        INGREDIENTS.clear()

        val storeFile = Gdx.files.local(fileName)
        val input = DataInputStream(storeFile.read())
        var count = input.readInt()
        while (count-- > 0) {
            val ingredient = Ingredient()
            ingredient.load(input)
            INGREDIENTS.add(ingredient)
        }
    }

    fun save() {
        val storeFile = Gdx.files.local(fileName)
        val output = DataOutputStream(storeFile.write(false))
        output.writeInt(INGREDIENTS.size)
        INGREDIENTS.forEach { it.save(output) }
    }
}