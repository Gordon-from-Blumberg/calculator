package com.gordonfromblumberg.calculator.util

import com.badlogic.gdx.tools.texturepacker.TexturePacker

object TextureAtlasPacker {
    @JvmStatic
    fun main(args: Array<String>) {
        TexturePacker.process("src/main/resources/image", "../assets/image", "texture_pack")
    }
}