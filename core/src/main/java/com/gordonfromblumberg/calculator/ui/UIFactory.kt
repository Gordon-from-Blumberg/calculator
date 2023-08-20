package com.gordonfromblumberg.calculator.ui

import com.badlogic.gdx.Input
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener

object UIFactory {
    private val floatFieldFilter = TextField.TextFieldFilter { field, c ->
        Character.isDigit(c)
                || (c == '.' || c == ',') && !containsDecimalPoint(field.text)
    }

    fun floatField(skin: Skin, value: String = "", listener: ((value: Float) -> Unit)? = null): TextField {
        val field = TextField(value, skin)
        field.textFieldFilter = floatFieldFilter
        field.setTextFieldListener { f, _ -> listener?.invoke(emptyToZero(f.text)) }
        return field
    }

    fun textField(skin: Skin, value: String = "", listener: ((value: String) -> Unit)? = null): TextField {
        val field = TextField(value, skin)
        field.setTextFieldListener { f, _ -> listener?.invoke(f.text) }
        return field
    }

    fun textButton(skin: Skin, text: String, handler: Runnable): TextButton {
        val button = TextButton(text, skin)
        button.addListener(object: ClickListener(Input.Buttons.LEFT) {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                handler.run()
            }
        })
        return button
    }

    private fun containsDecimalPoint(text: String) = text.contains('.') || text.contains(',')

    private fun emptyToZero(value: String): Float {
        return if (value.isEmpty()) 0f else value.toFloat()
    }
}