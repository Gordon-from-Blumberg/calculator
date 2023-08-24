package com.gordonfromblumberg.calculator.ui

import com.badlogic.gdx.scenes.scene2d.ui.Dialog
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.gordonfromblumberg.calculator.Config

class RunnableDialog(title: String, skin: Skin) : Dialog(title, skin) {
    init {
        contentTable.pad(Config.edgePad)
        buttonTable.pad(Config.edgePad)
    }

    override fun result(obj: Any?) {
        if (obj is Runnable)
            obj.run()
    }
}