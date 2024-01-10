package com.gordonfromblumberg.calculator.util

inline fun <T> loop(iterable: Iterable<T>, body: (T, Int) -> Unit) {
    var i = 0
    val it = iterable.iterator()
    while (it.hasNext()) {
        body(it.next(), i++)
    }
}