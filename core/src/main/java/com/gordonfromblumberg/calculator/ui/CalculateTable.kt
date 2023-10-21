package com.gordonfromblumberg.calculator.ui

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.gordonfromblumberg.calculator.Config
import com.gordonfromblumberg.calculator.Texts
import com.gordonfromblumberg.calculator.model.Ingredient

class CalculateTable(skin: Skin, private val ingredient: Ingredient) : Table(skin) {
    private val pfcLabel: Label
    private val kcalLabel: Label

    init {
        defaults().space(Config.cellSpace)
        columnDefaults(0).right().padRight(10f)
        columnDefaults(1).left()

        add(Texts.pfcPer100)
        add(ingredient.pfcPer100)

        row()
        add(Texts.kcalPer100)
        add(ingredient.kcalsPer100Rounded)

        row().padTop(15f)
        add(Texts.pfc)
        pfcLabel = Label(ingredient.pfc(ingredient.mass), skin)
        add(pfcLabel)

        row()
        add(Texts.kcal)
        kcalLabel = Label(ingredient.kcalsStr(ingredient.mass), skin)
        add(kcalLabel)

        row().padTop(15f)
        add(Texts.massCol)
        add(createMassField(ingredient.mass.toString())).fillX()
    }

    private fun createMassField(mass: String): TextField {
        return UIFactory.floatField(skin, mass) {
            pfcLabel.setText(ingredient.pfc(it))
            kcalLabel.setText(ingredient.kcalsStr(it))
        }
    }
}