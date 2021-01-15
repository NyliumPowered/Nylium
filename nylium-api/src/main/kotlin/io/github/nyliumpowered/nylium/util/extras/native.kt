package io.github.nyliumpowered.nylium.util.extras

import io.github.nyliumpowered.nylium.util.Location
import net.minecraft.entity.Entity

inline val Entity.location
    get() = Location.of(this)