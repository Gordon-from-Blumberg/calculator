package com.gordonfromblumberg.calculator

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Dialog
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.gordonfromblumberg.calculator.model.Ingredient
import com.gordonfromblumberg.calculator.ui.EditIngredientTable
import com.gordonfromblumberg.calculator.ui.IngredientTable
import com.gordonfromblumberg.calculator.ui.RunnableDialog
import com.gordonfromblumberg.calculator.ui.UIFactory

class CalculatorScreen : Screen {
    private val batch = SpriteBatch()
    private val viewport = ExtendViewport(0f, 0f)
    private val stage = Stage(viewport, batch)

    private val table: IngredientTable
    private val buttonTable: Table

    init {
        val skin = CalculatorApp.ASSETS.get("ui/uiskin.json", Skin::class.java)
        table = IngredientTable(skin)
        stage.addActor(table)
        buttonTable = buildButtonTable(skin)
    }

    override fun show() {
        Gdx.input.inputProcessor = stage

        configureViewport()
        val skin = CalculatorApp.ASSETS.get("ui/uiskin.json", Skin::class.java)
        buildTable()

        stage.isDebugAll = CalculatorApp.DEBUG
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(Config.backgroundColor)
        stage.act()
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
        resizeUI()
    }

    private fun configureViewport() {
        val minWorldWidth = Config.minWorldWidth
        val maxWorldWidth = Config.maxWorldWidth
        val minWorldHeight = minWorldWidth * Config.minRatio
        val maxWorldHeight = maxWorldWidth * Config.maxRatio
        viewport.minWorldWidth = minWorldWidth
        viewport.maxWorldWidth = maxWorldWidth
        viewport.minWorldHeight = minWorldHeight
        viewport.maxWorldHeight = maxWorldHeight
        val camera = OrthographicCamera()
        camera.setToOrtho(false)
        viewport.camera = camera
    }

    private fun buildTable() {
        TableScheme.validate()

        table.addIngredient(Ingredient("Chicken", 15f, 12f, 3f, 200f, 800f))
        table.addIngredient(Ingredient("Sugar", 0f, 1f, 60f, 20f, 20f))
        table.addIngredient(Ingredient("Oil", 0f, 80f, 10f, 500f, 50f))
    }

    private fun buildButtonTable(skin: Skin): Table {
        val addButton = UIFactory.textButton(skin, Texts.addButton, this::createAndShowAddDialog)
        val calculateButton = TextButton(Texts.calculateButton, skin)

        val buttonTable = Table(skin)
        buttonTable.add(addButton).expandX().fillX()
        buttonTable.add(calculateButton).expandX().fillX()

        stage.addActor(buttonTable)

        return buttonTable
    }

    private fun resizeUI() {
        table.setSize(viewport.worldWidth, viewport.worldHeight - Config.bottomPad)
        table.y = Config.bottomPad
        buttonTable.setSize(viewport.worldWidth - 2 * Config.edgePad,
                Config.bottomPad - 2 * Config.edgePad)
        buttonTable.setPosition(Config.edgePad, Config.edgePad)
    }

    private fun createAndShowAddDialog() {
        val skin = CalculatorApp.ASSETS.get("ui/uiskin.json", Skin::class.java)
        val dialog: Dialog = RunnableDialog(Texts.addIngredientTitle, skin)
        val ingredient = Ingredient()
        dialog.contentTable.add(EditIngredientTable(skin, ingredient))
        dialog.button(Texts.addButton, Runnable { table.addIngredient(ingredient) })
        dialog.show(stage)
    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun hide() {

    }

    override fun dispose() {

    }
}