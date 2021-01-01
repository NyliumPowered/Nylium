package io.github.nyliumpowered.nylium.text

import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.format.Style
import net.kyori.adventure.text.format.StyleBuilderApplicable
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.util.RGBLike
import net.minecraft.util.Identifier

object Components {
    @JvmStatic
    inline fun builder(builder: Builder.() -> Unit) {
        TODO("Not yet implemented.")
    }

    interface Builder {
        val empty: Component
        val newline: Component
        val space: Component

        fun append(content: String)
        fun append(content: String, style: StyleBuilder.() -> Unit)

        fun append(component: ComponentLike)
        fun append(vararg component: ComponentLike)
    }

    infix operator fun Builder.plus(content: String) = this.append(content)

    infix operator fun Builder.plus(component: ComponentLike) = this.append(component)

    interface StyleBuilder {
        var color: TextColor
        var font: Key?

        fun apply(applicable: StyleBuilderApplicable)

        fun color(hex: String)
        fun color(intValue: Int)
        fun color(rgb: RGBLike)
        fun color(r: Int, g: Int, b: Int)

        fun font(id: String)
        fun font(id: Identifier)

        fun decorate(vararg decoration: TextDecoration)
        fun decoration(decoration: TextDecoration, flag: Boolean)
        fun decoration(decoration: TextDecoration, state: TextDecoration.State)

        fun merge(that: Style): StyleBuilder
        fun merge(that: Style, strategy: Style.Merge.Strategy): StyleBuilder
        fun merge(that: Style, vararg merges: Style.Merge): StyleBuilder
        fun merge(that: Style, strategy: Style.Merge.Strategy, vararg merges: Style.Merge): StyleBuilder
    }
}