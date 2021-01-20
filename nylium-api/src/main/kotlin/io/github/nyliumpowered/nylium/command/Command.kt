package io.github.nyliumpowered.nylium.command

import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.builder.ArgumentBuilder
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder
import com.mojang.brigadier.tree.CommandNode
import io.github.nyliumpowered.nylium.util.AccessLevel
import net.minecraft.server.command.ServerCommandSource
import java.util.function.Consumer

interface Command {
    val access: AccessLevel
    val labels: Set<String>
    fun build(literal: LiteralArgumentBuilder<ServerCommandSource>): CommandNode<ServerCommandSource>

    companion object {
        @JvmStatic
        fun builder(vararg labels: String, callback: Consumer<BuilderScope>) =
                builder(AccessLevel.EVERYONE, *labels, scope = callback::accept)

        @JvmStatic
        fun builder(access: AccessLevel, vararg labels: String, consumer: Consumer<BuilderScope>) =
                builder(access, *labels, scope = consumer::accept)

        @JvmSynthetic
        fun builder(access: AccessLevel = AccessLevel.EVERYONE, vararg labels: String, scope: BuilderScope.() -> Unit) {
            TODO("Not yet implemented.")
        }

        const val SUCCEEDED = 1
        const val FAILED = -1
        const val AWAITING = 0
    }

    interface BuilderScope {
        fun literal(
                name: String
        ): LiteralArgumentBuilder<ServerCommandSource>

        fun literal(
                name: String, builder: LiteralArgumentBuilder<ServerCommandSource>.() -> Unit
        ): LiteralArgumentBuilder<ServerCommandSource>

        fun <T> argument(
                name: String, type: ArgumentType<T>
        ): RequiredArgumentBuilder<ServerCommandSource, T>

        fun <T> argument(
                name: String, type: ArgumentType<T>, builder: RequiredArgumentBuilder<ServerCommandSource, T>.() -> Unit
        ): RequiredArgumentBuilder<ServerCommandSource, T>

        @JvmSynthetic
        infix fun ArgumentBuilder<ServerCommandSource, *>.then(argument: ArgumentBuilder<ServerCommandSource, *>)

        @JvmSynthetic
        infix fun ArgumentBuilder<ServerCommandSource, *>.then(arguments: List<ArgumentBuilder<ServerCommandSource, *>>)
    }
}