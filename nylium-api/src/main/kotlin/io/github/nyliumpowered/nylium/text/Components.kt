package io.github.nyliumpowered.nylium.text

import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.format.Style
import net.kyori.adventure.text.format.StyleBuilderApplicable
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.kyori.adventure.util.RGBLike
import net.minecraft.util.Identifier
import net.minecraft.text.LiteralText as NativeLiteralText
import net.minecraft.text.Text as NativeText

interface Components {
    companion object {
        fun builder(builder: Builder.() -> Unit): Component {
            TODO("Not yet implemented.")
        }

        @JvmStatic
        fun toNativeText(component: Component): NativeText = toNativeTextOrNull(component) ?: NativeLiteralText.EMPTY

        @JvmStatic
        fun toNativeTextOrNull(component: Component): NativeText? = try {
            NativeText.Serializer.fromJson(GsonComponentSerializer.gson().serialize(component))
        } catch (ex: JsonParseException) {
            null
        }

        @JvmStatic
        fun fromNativeText(native: NativeText): Component {
            return GsonComponentSerializer.gson().deserialize(NativeText.Serializer.toJson(native))
        }

        @JvmStatic
        fun fromNativeTextOrNull(native: NativeText): Component? = try {
            GsonComponentSerializer.gson().deserialize(NativeText.Serializer.toJson(native))
        } catch (ex: JsonIOException) {
            null
        }
    }

    interface Builder {
        val base: TextComponent.Builder

        fun newline(amount: Int = 1)
        fun space(amount: Int = 1)

        fun append(content: String)
        fun append(content: String, style: StyleBuilder.() -> Unit)

        fun append(component: ComponentLike)
        fun append(vararg component: ComponentLike)
    }

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