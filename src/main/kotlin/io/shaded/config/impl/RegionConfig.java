package io.shaded.config.impl;

import io.shaded.ShadedRegionPlugin;

import java.util.Arrays;
import java.util.List;

public class RegionConfig {

    private static final transient RegionConfig instance = new RegionConfig();

    public static List<String> regions = Arrays.asList("spawn");

    public static void load() {
        ShadedRegionPlugin.getConfigSerializer().load(instance, RegionConfig.class, "region-config");
    }

    public static void save() {
        ShadedRegionPlugin.getConfigSerializer().save(instance, "region-config");
    }

}
