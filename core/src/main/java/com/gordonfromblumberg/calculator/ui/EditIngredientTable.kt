package com.gordonfromblumberg.calculator.ui

import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.gordonfromblumberg.calculator.Config
import com.gordonfromblumberg.calculator.Texts
import com.gordonfromblumberg.calculator.model.Ingredient

class EditIngredientTable(skin: Skin, val ingredient: Ingredient) : Table(skin) {
    init {
        defaults().space(Config.cellSpace)

        add(Texts.nameCol).right()
        add(UIFactory.textField(skin, ingredient.name) { ingredient.name = it })

        row()
        add(Texts.proteinsCol).right()
        add(UIFactory.floatField(skin, ingredient.proteinsPer100Rounded()) { ingredient.proteinsPer100 = it })

        row()
        add(Texts.fatsCol).right()
        add(UIFactory.floatField(skin, ingredient.fatsPer100Rounded()) { ingredient.fatsPer100 = it })

        row()
        add(Texts.carbohydratesCol).right()
        add(UIFactory.floatField(skin, ingredient.chsPer100Rounded()) { ingredient.chsPer100 = it })

        row()
        add(Texts.kcalCol).right()
        add(UIFactory.floatField(skin, ingredient.kcalsPer100Rounded()) { ingredient.kcalsPer100 = it })

        row()
        add(Texts.massCol).right()
        add(UIFactory.floatField(skin, ingredient.mass.toString()) { ingredient.mass = it })
    }
}