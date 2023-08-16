package com.gordonfromblumberg.calculator.ui

import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.gordonfromblumberg.calculator.model.Ingredient

class CalculatorTable(skin: Skin) : Table(skin) {
    private val ingredients = ArrayList<Ingredient>()
}