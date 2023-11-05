package com.gordonfromblumberg.calculator.ui

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.ui.Cell
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.gordonfromblumberg.calculator.Config

open class ScrollableTable(skin: Skin,
                      headerBackgroundColor: Color,
                      protected val table: Table = Table(skin)) : ScrollPane(table) {

    companion object {
        private fun emptyConfigure(cell: Cell<Label>, index: Int) = cell
    }

    private val headerBackground = UIFactory.colorDrawable(headerBackgroundColor)
    private lateinit var headers: Array<Cell<Label>>

    init {
//        for (i in 1..30) {
//            table.row()
//            table.add("Line $i")
//        }
    }

    constructor(table: Table, headerBackgroundColor: Color) :
            this(table.skin, headerBackgroundColor, table)

    fun setHeaders(headers: Array<String>, configure: (Cell<Label>, Int) -> Cell<Label> = ::emptyConfigure) {
        this.headers = Array(headers.size) {
            configure(table.add(headers[it]), it)
        }
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)

        val x = this.x
        val y = this.y + this.height - table.getRowHeight(0) - Config.edgePad
        val blendingEnabled = batch?.isBlendingEnabled ?: false
        batch?.disableBlending()
        headerBackground.draw(batch, x, y, table.width, table.getRowHeight(0))
        if (blendingEnabled) batch?.enableBlending()
    }
}