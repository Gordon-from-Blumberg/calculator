package com.gordonfromblumberg.calculator.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions
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

    override fun show(stage: Stage?): Dialog {
        super.show(stage)
        addAction(Actions.moveBy(0f, (stage!!.viewport.worldHeight - height) / 4, .3f))
        return this
    }

    override fun hide() {
        super.hide()
        Gdx.input.setOnscreenKeyboardVisible(false)
    }
}