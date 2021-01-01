package io.shaded.config.impl

import io.shaded.ShadedRegionPlugin.Companion.configSerializer
import java.util.*

class RegionConfig : Config() {

    fun load() {
        configSerializer!!.load(instance, Config::class.java, "region-config")
    }

    fun save() {
        configSerializer!!.save(instance, "region-config")
    }

    companion object {
        @Transient
        val instance = RegionConfig()
    }

}


open class Config {
    var regions = Arrays.asList("spawn")
}

