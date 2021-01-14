package io.github.nyliumpowered.nylium.base.text

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.TextComponent
import io.github.nyliumpowered.nylium.text.Components as ApiComponents

class Components {
    class Builder(override val base: TextComponent.Builder) : ApiComponents.BaseBuilder {
        override fun newline(amount: Int) = repeat(amount) {
            base.append(Component.newline())
        }

        override fun whitespace(amount: Int) = repeat(amount) {
            base.append(Component.space())
        }

        override fun append(content: String) {
            base.append(Component.text(content))
        }

        override fun append(content: String, style: ApiComponents.StyleBuilder.() -> Unit) {

        }

        override fun append(component: ComponentLike) {
            TODO("Not yet implemented")
        }

        override fun append(vararg component: ComponentLike) {
            TODO("Not yet implemented")
        }

    }
}