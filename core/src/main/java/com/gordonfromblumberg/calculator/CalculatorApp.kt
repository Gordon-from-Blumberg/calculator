package com.gordonfromblumberg.calculator

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx

class CalculatorApp : Game() {
    override fun create() {
        Gdx.graphics.isContinuousRendering = false

        setScreen(CalculatorScreen())
    }
}