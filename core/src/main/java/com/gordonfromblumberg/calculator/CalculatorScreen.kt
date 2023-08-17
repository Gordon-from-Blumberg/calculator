package com.gordonfromblumberg.calculator

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.gordonfromblumberg.calculator.model.Ingredient
import com.gordonfromblumberg.calculator.ui.IngredientTable

class CalculatorScreen : Screen {
    private val batch = SpriteBatch()
    private val viewport = ExtendViewport(0f, 0f)
    private val stage = Stage(viewport, batch)

    private val table: IngredientTable
    private val button: TextButton

    init {
        val skin = CalculatorApp.ASSETS.get("ui/uiskin.json", Skin::class.java)
        table = IngredientTable(skin)
        button = TextButton("ADD", skin)
    }

    override fun show() {
        Gdx.input.inputProcessor = stage

        configureViewport()
        val skin = CalculatorApp.ASSETS.get("ui/uiskin.json", Skin::class.java)
        buildTable(skin)
        buildButton(skin)

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

    private fun buildTable(skin: Skin) {
        TableScheme.validate()

//        for (row in TableScheme.rows) {
//            val fillY = row.fill
//            for (cell in row.cells) {
//                val widget: Actor = when (cell.id) {
//                    "display" -> Label("0", skin)
//                    else -> TextButton(cell.id, skin)
//                }
//                table.add(widget)
//                        .colspan(cell.colSpan).fill(1f, fillY)
//            }
//            table.row()
//        }
        table.addIngredient(Ingredient("Chicken", 15f, 12f, 3f, 200f, 800f))
        table.addIngredient(Ingredient("Sugar", 0f, 1f, 60f, 20f, 20f))
        table.addIngredient(Ingredient("Oil", 0f, 80f, 10f, 500f, 50f))

//        table.setFillParent(true)

        stage.addActor(table)
    }

    private fun buildButton(skin: Skin) {

        stage.addActor(button)
    }

    private fun resizeUI() {
        table.setSize(viewport.worldWidth, viewport.worldHeight - Config.bottomPad)
        table.y = Config.bottomPad

        button.width = viewport.worldWidth / 2
        button.height = Config.bottomPad - 2 * Config.edgePad
        button.x = (viewport.worldWidth - button.width) / 2
        button.y = Config.edgePad
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