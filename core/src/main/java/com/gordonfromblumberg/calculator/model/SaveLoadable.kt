package com.gordonfromblumberg.calculator.model

import java.io.DataInputStream
import java.io.DataOutputStream

interface SaveLoadable {
    fun save(out: DataOutputStream)
    fun load(input: DataInputStream)
}