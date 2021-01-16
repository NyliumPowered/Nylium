package io.github.nyliumpowered.nylium.base.text

import io.github.nyliumpowered.nylium.Nylium
import io.github.nyliumpowered.nylium.util.Location
import io.github.nyliumpowered.nylium.util.extras.toBlockNbtComponentWorldPos
import io.github.nyliumpowered.nylium.util.extras.toKey
import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.format.Style
import net.kyori.adventure.text.format.StyleBuilderApplicable
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.util.RGBLike
import net.minecraft.util.Identifier
import io.github.nyliumpowered.nylium.text.ComponentBuilder as ApiComponentBuilder
import io.github.nyliumpowered.nylium.text.ComponentStyleBuilder as ApiStyleBuilder

object BaseComponents {
    class ComponentBuilder : ApiComponentBuilder {
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

        override fun text(content: String, style: ApiStyleBuilder.() -> Unit) {
            base.append(Component.text(content).style(style))
        }

        override fun score(name: String, objective: String, value: String) {
            base.append(Component.score(name, objective, value))
        }

        override fun score(name: String, objective: String, value: String, style: ApiStyleBuilder.() -> Unit) {
            base.append(Component.score(name, objective, value).style(style))
        }

        override fun selector(pattern: String) {
            base.append(Component.selector(pattern))
        }

        override fun selector(pattern: String, style: ApiStyleBuilder.() -> Unit) {
            base.append(Component.selector(pattern).style(style))
        }

        override fun blockNbt(path: String, location: Location) {
            base.append(Component.blockNBT(path, location.toBlockNbtComponentWorldPos()))
        }

        override fun blockNbt(path: String, location: Location, style: ApiStyleBuilder.() -> Unit) {
            base.append(Component.blockNBT(path, location.toBlockNbtComponentWorldPos()).style(style))
        }

        override fun entityNbt(path: String, selector: String) {
            base.append(Component.entityNBT(path, selector))
        }

        override fun entityNbt(path: String, selector: String, style: ApiStyleBuilder.() -> Unit) {
            base.append(Component.entityNBT(path, selector).style(style))
        }

        override fun storageNbt(path: String, key: Key) {
            base.append(Component.storageNBT(path, key))
        }

        override fun storageNbt(path: String, key: Key, style: ApiStyleBuilder.() -> Unit) {
            base.append(Component.storageNBT(path, key).style(style))
        }

        override fun keybind(key: String) {
            base.append(Component.keybind(key))
        }

        override fun keybind(key: String, style: ApiStyleBuilder.() -> Unit) {
            base.append(Component.keybind(key).style(style))
        }

        override fun translatable(key: String) {
            base.append(Component.translatable(key))
        }

        override fun translatable(key: String, style: ApiStyleBuilder.() -> Unit) {
            base.append(Component.translatable(key).style(style))
        }

        override fun build() = base.build()
    }

    private fun Component.style(style: ApiStyleBuilder.() -> Unit) = apply {
        style(Nylium.instance.builders(ApiStyleBuilder::class.java, style))
    }

    class ComponentStyleBuilder : ApiStyleBuilder {
        private val base = Style.style()

        override var color: TextColor? = null
            set(value) {
                base.color(value)
            }

        override var font: Key? = null
            set(value) {
                base.font(value)
            }

        override fun apply(applicable: StyleBuilderApplicable) {
            base.apply(applicable)
        }

        override fun color(hex: String) {
            base.color(TextColor.fromHexString(hex))
        }

        override fun color(intValue: Int) {
            base.color(TextColor.color(intValue))
        }

        override fun color(rgb: RGBLike) {
            base.color(TextColor.color(rgb))
        }

        override fun color(r: Int, g: Int, b: Int) {
            base.color(TextColor.color(r, g, b))
        }

        override fun font(id: String) {
            base.font(Key.key(id))
        }

        override fun font(id: Identifier) {
            base.font(id.toKey())
        }

        override fun decorate(vararg decoration: TextDecoration) {
            base.decorate(*decoration)
        }

        override fun decoration(decoration: TextDecoration, flag: Boolean) {
            base.decoration(decoration, TextDecoration.State.byBoolean(flag))
        }

        override fun decoration(decoration: TextDecoration, state: TextDecoration.State) {
            base.decoration(decoration, state)
        }

        override fun merge(that: Style) {
            base.merge(that)
        }

        override fun merge(that: Style, strategy: Style.Merge.Strategy) {
            base.merge(that, strategy)
        }

        override fun merge(that: Style, vararg merges: Style.Merge) {
            base.merge(that, *merges)
        }

        override fun merge(that: Style, strategy: Style.Merge.Strategy, vararg merges: Style.Merge) {
            base.merge(that, strategy, *merges)
        }

        override fun build() = base.build()
    }
}
