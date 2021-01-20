package io.github.nyliumpowered.nylium

import io.github.nyliumpowered.nylium.util.BuilderFactory
import java.util.function.Supplier

//TODO: Document.
interface BuilderRegistry {
    val entries: Map<Class<*>, Supplier<*>>

    fun <T : BuilderFactory<*>, S : T> register(clazz: Class<T>, supplier: () -> S)

    @Throws(IllegalArgumentException::class)
    operator fun <T> get(clazz: Class<T>): T

    fun <T> getOrNull(clazz: Class<T>): T?

    operator fun <I, R, T : BuilderFactory<R>> invoke(clazz: Class<T>, builder: Function<I>): R
}