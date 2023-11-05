package com.gordonfromblumberg.calculator.ui

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.scenes.scene2d.ui.Dialog
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Array
import com.gordonfromblumberg.calculator.Config
import com.gordonfromblumberg.calculator.Texts
import com.gordonfromblumberg.calculator.model.Ingredient

class IngredientTable(skin: Skin, headerBackgroundColor: Color) :
        ScrollableTable(Table(skin), headerBackgroundColor) {

    private val ingredients = Array<Ingredient>()
    val total = Ingredient(Texts.total)

    companion object {
        const val EDIT = "EDIT"
        const val REMOVE = "REMOVE"
    }

    init {
        table.top().pad(Config.edgePad)
        table.defaults().space(Config.cellSpace)
        buildHeader()
        addListener(ClickHandler())
    }

    fun addIngredient(ingredient: Ingredient) {
        ingredients.add(ingredient)
        rebuild()
    }

    fun clearIngredients() {
        ingredients.clear()
        rebuild()
    }

    private fun rebuild() {
        table.clearChildren()
        total.reset()

        buildHeader()
        for (ing in ingredients) {
            addIngredientRow(ing)
            table.add(rowButton(Texts.edit, ing, EDIT)).minWidth(25f)
            table.add(rowButton(Texts.remove, ing, REMOVE)).minWidth(25f)
            total.add(ing)
        }

        addIngredientRow(total)
    }

    private fun buildHeader() {
        table.row().top()
        table.add(Texts.nameCol).expandX()
        table.add(Texts.proteinsCol)
        table.add(Texts.fatsCol)
        table.add(Texts.carbohydratesCol)
        table.add(Texts.kcalCol)
        table.add(Texts.massCol)
    }

    private fun addIngredientRow(ingredient: Ingredient) {
        table.row()
        table.add(ingredient.name)
        table.add(ingredient.proteinsPer100Rounded)
        table.add(ingredient.fatsPer100Rounded)
        table.add(ingredient.chsPer100Rounded)
        table.add(ingredient.kcalsPer100Rounded)
        table.add(ingredient.mass.toString())
    }

    private fun rowButton(text: String, ingredient: Ingredient, name: String): TextButton {
        val button = TextButton(text, table.skin)
        button.name = name
        button.userObject = ingredient
        return button
    }

    private inner class ClickHandler : ClickListener() {
        override fun clicked(event: InputEvent, x: Float, y: Float) {
            val button: Button? = event.target.firstAscendant(Button::class.java)
            if (button != null) {
                when (button.name) {
                    EDIT -> {
                        val ingredient = button.userObject as Ingredient
                        val dialog: Dialog = RunnableDialog(Texts.editIngredientTitle, table.skin)
                        dialog.contentTable.add(EditIngredientTable(table.skin, ingredient))
                        dialog.button(Texts.saveButton, Runnable { rebuild() })
                        dialog.show(stage)
                    }
                    REMOVE -> {
                        ingredients.removeValue(button.userObject as Ingredient, true)
                        rebuild()
                    }
                }
            }
        }
    }
}