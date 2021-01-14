package io.github.nyliumpowered.nylium.util

//TODO: Document.
interface BuilderFactory<out R> {
    fun build(): R
}