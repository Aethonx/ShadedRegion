package io.shaded.config;

import io.shaded.ShadedRegionPlugin;

import java.io.File;
import java.util.logging.Logger;

public class Serializer {

    private final boolean data;
    private final File dataFolder;
    private final Logger logger;

    public Serializer(boolean data, File dataFolder, Logger logger) {
        this.data = data;
        this.dataFolder = dataFolder;
        this.logger = logger;
    }

    public Serializer(boolean data) {
        this.data = data;
        this.dataFolder = ShadedRegionPlugin.Companion.getInstance().getDataFolder();
        this.logger = ShadedRegionPlugin.Companion.getInstance().getLogger();
    }

    public Serializer() {
        this.data = false;
        this.dataFolder = ShadedRegionPlugin.Companion.getInstance().getDataFolder();
        this.logger = ShadedRegionPlugin.Companion.getInstance().getLogger();
    }

    /**
     * Saves your class to a .json file.
     */
    public void save(Object instance, String name) {
        new Persist(this.dataFolder, logger).save(data, instance, name);
    }

    public void save(Object instance) {
        new Persist(this.dataFolder, logger).save(data, instance);
    }

    public void save(Object instance, File file) {
        new Persist(this.dataFolder, logger).save(data, instance, file);
    }

    /**
     * Loads your class from a json file
     */
    public <T> T load(T def, Class<T> clazz, String name) {
        return new Persist(dataFolder, logger).loadOrSaveDefault(data, def, clazz, name);
    }

    public <T> T load(T def, Class<T> clazz, File file) {
        return new Persist(dataFolder, logger).loadOrSaveDefault(data, def, clazz, file);
    }


}