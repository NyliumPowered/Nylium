package io.github.nyliumpowered.nylium

import com.google.inject.Inject
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.SharedConstants
import org.apache.logging.log4j.Logger

interface Nylium {
    val logger: Logger

    companion object {
        @Inject
        private var impl: Nylium? = null

        @JvmStatic
        val instance: Nylium
            get() = impl ?: throw IllegalStateException("Nylium hasn't been initialized yet, It's too soon to access it!")

        @JvmStatic
        val isDev
            get() = SharedConstants.isDevelopment || FabricLoader.getInstance().isDevelopmentEnvironment
    }
}