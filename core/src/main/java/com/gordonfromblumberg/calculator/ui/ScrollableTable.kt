package com.gordonfromblumberg.calculator.ui

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.ui.Cell
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.Array
import com.gordonfromblumberg.calculator.Config
import com.gordonfromblumberg.calculator.util.loop

open class ScrollableTable(headerBackgroundColor: Color,
                           protected val table: HeaderedTable) : ScrollPane(table) {

    companion object {
        private fun emptyConfigure(cell: Cell<Label>, index: Int) = cell
    }

    private val headerBackground = UIFactory.colorDrawable(headerBackgroundColor)

    init {
//        for (i in 1..30) {
//            table.row()
//            table.add("Line $i")
//        }
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)

        val x = this.x
        val y = this.y + this.height - table.getRowHeight(0) - Config.edgePad
        headerBackground.draw(batch, x, y, table.width, table.getRowHeight(0))

        for (headerCell in table.headerCells) {
            val header = headerCell.actor!!
            val headerX = header.x
            val headerY = header.y
        }
    }
}