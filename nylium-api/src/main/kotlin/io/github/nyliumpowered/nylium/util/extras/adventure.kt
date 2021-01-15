package io.github.nyliumpowered.nylium.util.extras

import io.github.nyliumpowered.nylium.util.Location
import net.kyori.adventure.key.Key
import net.kyori.adventure.text.BlockNBTComponent
import net.minecraft.util.Identifier

fun Location.toBlockNbtComponentWorldPos() = BlockNBTComponent.WorldPos.of(
        BlockNBTComponent.WorldPos.Coordinate.absolute(x.toInt()),
        BlockNBTComponent.WorldPos.Coordinate.absolute(y.toInt()),
        BlockNBTComponent.WorldPos.Coordinate.absolute(z.toInt()),
)

fun Key.toIdentifier() = Identifier(namespace(), value())

fun Identifier.toKey() = Key.key(namespace, path)