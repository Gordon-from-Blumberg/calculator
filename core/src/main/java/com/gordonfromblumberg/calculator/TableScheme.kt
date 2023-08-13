package com.gordonfromblumberg.calculator


object TableScheme {
    val rows = arrayOf(
            Row(0f, arrayOf(Cell( "display", 4))),
            Row(0f, arrayOf(Cell("-"), Cell("+"), Cell("/"), Cell("*")))
    )

    fun validate() {
        val firstRowWidth = rows[0].width()
        for (i in 1 until rows.size) {
            val width = rows[i].width()
            if (width != firstRowWidth) {
                throw IllegalStateException("Widths of rows does not equal: $firstRowWidth of #0 and $width of #$i")
            }
        }
    }
}

class Row(val fill: Float, val cells: Array<Cell>) {
    fun width() = cells.fold(0) { sum, cell -> sum + cell.colSpan }
}

class Cell(val id: String, val colSpan: Int = 1)