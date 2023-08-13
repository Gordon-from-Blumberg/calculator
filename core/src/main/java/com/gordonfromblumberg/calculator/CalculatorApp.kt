package com.gordonfromblumberg.calculator

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.ui.Skin


class CalculatorApp : Game() {
    companion object {
        var DEBUG = false
        lateinit var INSTANCE: CalculatorApp
        val ASSETS = AssetManager()
    }

    init {
        INSTANCE = this
    }

    override fun create() {
        Gdx.graphics.isContinuousRendering = false
        ASSETS.load("ui/uiskin.atlas", TextureAtlas::class.java)
        ASSETS.load("ui/uiskin.json", Skin::class.java)
//        ASSETS.load("image/texture_pack.atlas", TextureAtlas::class.java)
        ASSETS.finishLoading()

        setScreen(CalculatorScreen())
    }
}