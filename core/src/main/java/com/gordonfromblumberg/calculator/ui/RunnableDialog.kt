package com.gordonfromblumberg.calculator.ui

import com.badlogic.gdx.scenes.scene2d.ui.Dialog
import com.badlogic.gdx.scenes.scene2d.ui.Skin

class RunnableDialog(title: String, skin: Skin) : Dialog(title, skin) {
    override fun result(obj: Any) {
        if (obj is Runnable)
            obj.run()
    }
}