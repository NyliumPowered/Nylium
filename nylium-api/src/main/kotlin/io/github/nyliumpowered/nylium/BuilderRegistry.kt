package io.github.nyliumpowered.nylium

import java.util.function.Supplier

interface BuilderRegistry {
    val entries: Map<Class<*>, Supplier<*>>

    fun <T, S : T> register(clazz: Class<T>, supplier: () -> S)

    @Throws(IllegalArgumentException::class)
    operator fun <T> get(clazz: Class<T>): T

    fun <T> getOrNull(clazz: Class<T>): T?
}