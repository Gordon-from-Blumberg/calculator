package com.gordonfromblumberg.calculator.ui

import com.badlogic.gdx.scenes.scene2d.ui.Cell
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.Array

open class HeaderedTable(skin: Skin,
                         private val headers: Array<HeaderDef>) : Table(skin) {
    val headerCells = Array<Cell<Label>>()

    init {
        buildHeaderRow()
    }

    override fun clearChildren() {
        super.clearChildren()
        headerCells.clear()
    }

    override fun reset() {
        super.reset()
        headerCells.clear()
        headers.clear()
    }

    protected fun buildHeaderRow() {
        for (hd in headers) {
            val cell = add(hd.name)
            if (hd.expand) {
                cell.expandX()
            }
            headerCells.add(cell)
        }
        row()
    }
}

class HeaderDef(val name: String, val expand: Boolean = false)