package io.github.nyliumpowered.nylium.util.extras

import io.github.nyliumpowered.nylium.text.Components
import io.github.nyliumpowered.nylium.util.Location
import net.kyori.adventure.text.Component
import net.minecraft.entity.Entity

inline val Entity.location
    get() = Location.of(this)

fun Component.toNative() = Components.toNativeText(this)
fun Component.toNativeOrNull() = Components.toNativeTextOrNull(this)