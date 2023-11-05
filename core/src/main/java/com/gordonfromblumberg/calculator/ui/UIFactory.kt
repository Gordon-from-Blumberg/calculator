package com.gordonfromblumberg.calculator.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.scenes.scene2d.ui.TextField.OnscreenKeyboard
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener

object UIFactory {
    private val floatFieldFilter = TextField.TextFieldFilter { field, c ->
        Character.isDigit(c)
                || (c == '.' || c == ',') && !containsDecimalPoint(field.text)
    }

    private val numberKeyboard = OnscreenKeyboard {
        Gdx.input.setOnscreenKeyboardVisible(it, Input.OnscreenKeyboardType.NumberPad)
    }

    private val focusListener = object : FocusListener() {
        override fun keyboardFocusChanged(event: FocusEvent?, actor: Actor?, focused: Boolean) {
            super.keyboardFocusChanged(event, actor, focused)
            if (actor is TextField) {
                actor.onscreenKeyboard.show(true)
            }
        }
    }

    fun floatField(skin: Skin, value: String = "", listener: ((value: Float) -> Unit)? = null): TextField {
        return TextField(value, skin).apply {
            textFieldFilter = floatFieldFilter
            setTextFieldListener { f, _ -> listener?.invoke(emptyToZero(f.text)) }
            addListener(focusListener)
            onscreenKeyboard = numberKeyboard
        }
    }

    fun textField(skin: Skin, value: String = "", listener: ((value: String) -> Unit)? = null): TextField {
        return TextField(value, skin).apply {
            setTextFieldListener { f, _ -> listener?.invoke(f.text) }
            addListener(focusListener)
        }
    }

    fun textField(skin: Skin, style: String, value: String = "", listener: ((value: String) -> Unit)? = null): TextField {
        return TextField(value, skin, style).apply {
            setTextFieldListener { f, _ -> listener?.invoke(f.text) }
            addListener(focusListener)
        }
    }

    fun textButton(skin: Skin, text: String, handler: Runnable): TextButton {
        return TextButton(text, skin).apply {
            addListener(object: ClickListener(Input.Buttons.LEFT) {
                override fun clicked(event: InputEvent?, x: Float, y: Float) {
                    handler.run()
                }
            })
        }
    }

    fun colorDrawable(color: Color): PixmapDrawable {
        val size = 2
        return Pixmap(size, size, Pixmap.Format.RGB888).run {
            setColor(color)
            fill()
            PixmapDrawable(this).also { this.dispose() }
        }
    }

    private fun containsDecimalPoint(text: String) = text.contains('.') || text.contains(',')

    private fun emptyToZero(value: String): Float {
        return if (value.isEmpty()) 0f else value.toFloat()
    }
}