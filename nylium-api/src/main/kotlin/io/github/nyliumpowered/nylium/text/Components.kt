package io.github.nyliumpowered.nylium.text

import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import io.github.nyliumpowered.nylium.Nylium
import io.github.nyliumpowered.nylium.util.BuilderFactory
import io.github.nyliumpowered.nylium.util.Location
import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.format.Style
import net.kyori.adventure.text.format.StyleBuilderApplicable
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.kyori.adventure.util.RGBLike
import net.minecraft.util.Identifier
import net.minecraft.text.LiteralText as NativeLiteralText
import net.minecraft.text.Text as NativeText

@JvmSynthetic
fun component(builder: ComponentBuilder.() -> Unit): Component =
        Nylium.instance.builders(ComponentBuilder::class.java, builder)

object Components {
    @JvmStatic
    fun toNativeText(component: Component): NativeText =
            toNativeTextOrNull(component) ?: NativeLiteralText.EMPTY

    @JvmStatic
    fun toNativeTextOrNull(component: Component): NativeText? = try {
        NativeText.Serializer.fromJson(GsonComponentSerializer.gson().serialize(component))
    } catch (ex: JsonParseException) {
        null
    }

    @JvmStatic
    fun fromNativeText(native: NativeText): Component =
            GsonComponentSerializer.gson().deserialize(NativeText.Serializer.toJson(native))

    @JvmStatic
    fun fromNativeTextOrNull(native: NativeText): Component? = try {
        GsonComponentSerializer.gson().deserialize(NativeText.Serializer.toJson(native))
    } catch (ex: JsonIOException) {
        null
    }
}

interface ComponentBuilder : BuilderFactory<Component> {
    fun newline(amount: Int = 1)
    fun whitespace(amount: Int = 1)

    fun append(component: ComponentLike)
    fun append(component: () -> ComponentLike)
    fun append(vararg component: ComponentLike)

    fun text(content: String)
    fun text(content: () -> String)
    fun text(content: String, style: ComponentStyleBuilder.() -> Unit)

    fun score(name: String, objective: String, value: String)
    fun score(name: String, objective: String, value: String, style: ComponentStyleBuilder.() -> Unit)

    fun selector(pattern: String)
    fun selector(pattern: String, style: ComponentStyleBuilder.() -> Unit)

    fun blockNbt(path: String, location: Location)
    fun blockNbt(path: String, location: Location, style: ComponentStyleBuilder.() -> Unit)

    fun entityNbt(path: String, selector: String)
    fun entityNbt(path: String, selector: String, style: ComponentStyleBuilder.() -> Unit)

    fun storageNbt(path: String, key: Key)
    fun storageNbt(path: String, key: Key, style: ComponentStyleBuilder.() -> Unit)

    fun keybind(key: String)
    fun keybind(key: String, style: ComponentStyleBuilder.() -> Unit)

    fun translatable(key: String)
    fun translatable(key: String, style: ComponentStyleBuilder.() -> Unit)
}

interface ComponentStyleBuilder {
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
    fun merge(that: Style): ComponentStyleBuilder

    fun merge(that: Style, strategy: Style.Merge.Strategy): ComponentStyleBuilder
    fun merge(that: Style, vararg merges: Style.Merge): ComponentStyleBuilder
    fun merge(that: Style, strategy: Style.Merge.Strategy, vararg merges: Style.Merge): ComponentStyleBuilder
}

fun NativeText.toComponent() = Components.fromNativeText(this)
fun NativeText.toComponentOrNull() = Components.fromNativeTextOrNull(this)
fun Component.toNative() = Components.toNativeText(this)
fun Component.toNativeOrNull() = Components.toNativeTextOrNull(this)