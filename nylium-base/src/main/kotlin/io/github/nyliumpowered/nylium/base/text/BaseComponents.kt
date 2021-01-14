package io.github.nyliumpowered.nylium.base.text

import io.github.nyliumpowered.nylium.text.ComponentBuilder
import io.github.nyliumpowered.nylium.text.ComponentStyleBuilder
import io.github.nyliumpowered.nylium.util.Location
import net.kyori.adventure.key.Key
import net.kyori.adventure.text.BlockNBTComponent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentLike

object BaseComponents {
    class BuilderFactory : ComponentBuilder {
        private val base = Component.text()

        override fun newline(amount: Int) = repeat(amount) {
            base.append(Component.newline())
        }

        override fun whitespace(amount: Int) = repeat(amount) {
            base.append(Component.space())
        }

        override fun append(component: ComponentLike) {
            base.append(component)
        }

        override fun append(component: () -> ComponentLike) {
            base.append(component())
        }

        override fun append(vararg component: ComponentLike) {
            base.append(*component)
        }

        override fun text(content: String) {
            base.append(Component.text(content))
        }

        override fun text(content: () -> String) {
            base.append(Component.text(content()))
        }

        override fun text(content: String, style: ComponentStyleBuilder.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun score(name: String, objective: String, value: String) {
            base.append(Component.score(name, objective, value))
        }

        override fun score(name: String, objective: String, value: String, style: ComponentStyleBuilder.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun selector(pattern: String) = append(Component.selector(pattern))

        override fun selector(pattern: String, style: ComponentStyleBuilder.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun blockNbt(path: String, location: Location) {
            base.append(Component.blockNBT(path, location.toAbsoluteWorldPos()))
        }

        override fun blockNbt(path: String, location: Location, style: ComponentStyleBuilder.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun entityNbt(path: String, selector: String) {
            base.append(Component.entityNBT(path, selector))
        }

        override fun entityNbt(path: String, selector: String, style: ComponentStyleBuilder.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun storageNbt(path: String, key: Key) {
            base.append(Component.storageNBT(path, key))
        }

        override fun storageNbt(path: String, key: Key, style: ComponentStyleBuilder.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun keybind(key: String) {
            base.append(Component.keybind(key))
        }

        override fun keybind(key: String, style: ComponentStyleBuilder.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun translatable(key: String) {
            base.append(Component.translatable(key))
        }

        override fun translatable(key: String, style: ComponentStyleBuilder.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun build() = base.build()
    }

    private fun Location.toAbsoluteWorldPos() = BlockNBTComponent.WorldPos.of(
            BlockNBTComponent.WorldPos.Coordinate.absolute(x.toInt()),
            BlockNBTComponent.WorldPos.Coordinate.absolute(y.toInt()),
            BlockNBTComponent.WorldPos.Coordinate.absolute(z.toInt()),
    )
}
