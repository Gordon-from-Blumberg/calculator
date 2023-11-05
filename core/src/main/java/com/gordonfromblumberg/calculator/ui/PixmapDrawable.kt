package com.gordonfromblumberg.calculator.ui

import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable

class PixmapDrawable(pixmap: Pixmap) : TextureRegionDrawable(Texture(pixmap))