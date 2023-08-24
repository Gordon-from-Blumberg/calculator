package com.gordonfromblumberg.calculator.ui

import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.gordonfromblumberg.calculator.Config
import com.gordonfromblumberg.calculator.Texts
import com.gordonfromblumberg.calculator.model.Ingredient

class EditIngredientTable(skin: Skin, private val ingredient: Ingredient) : Table(skin) {
    init {
        defaults().space(Config.cellSpace)
        columnDefaults(0).right()

        add(Texts.nameCol)
        add(UIFactory.textField(skin, ingredient.name) { ingredient.name = it })

        row()
        add(Texts.proteinsCol)
        add(UIFactory.floatField(skin, ingredient.proteinsPer100Rounded()) { ingredient.proteinsPer100 = it })

        row()
        add(Texts.fatsCol)
        add(UIFactory.floatField(skin, ingredient.fatsPer100Rounded()) { ingredient.fatsPer100 = it })

        row()
        add(Texts.carbohydratesCol)
        add(UIFactory.floatField(skin, ingredient.chsPer100Rounded()) { ingredient.chsPer100 = it })

        row()
        add(Texts.kcalCol)
        add(UIFactory.floatField(skin, ingredient.kcalsPer100Rounded()) { ingredient.kcalsPer100 = it })

        row()
        add(Texts.massCol)
        add(UIFactory.floatField(skin, ingredient.mass.toString()) { ingredient.mass = it })
    }
}