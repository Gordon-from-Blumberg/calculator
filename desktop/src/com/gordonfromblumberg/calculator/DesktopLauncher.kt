package com.gordonfromblumberg.calculator

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
object DesktopLauncher {
    @JvmStatic
    fun main(args: Array<String>) {
        val config = Lwjgl3ApplicationConfiguration()
        config.setForegroundFPS(60)
        config.setTitle("Calculator")
        config.setWindowedMode(Config.minWorldWidth.toInt(),
                (Config.minWorldWidth * Config.minRatio).toInt())

        for (arg in args) {
            if ("debug" == arg) {
                CalculatorApp.DEBUG = true
                continue
            }
        }

        Lwjgl3Application(CalculatorApp(), config)
    }
}