package com.github.argon4w.acceleratedrendering;

import com.github.argon4w.acceleratedrendering.configs.FeatureConfig;
import com.github.argon4w.acceleratedrendering.core.programs.ComputeShaderPrograms;
import com.github.argon4w.acceleratedrendering.features.culling.OrientationCullingPrograms;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import lombok.Getter;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoader;
import net.neoforged.fml.config.ModConfig;
import org.annotation.Delete;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AcceleratedRenderingModEntry implements ClientModInitializer {

    public static final String MOD_ID = "acceleratedrendering";

    @Getter
    private static ModContainer container;

    public static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    @Override
    public void onInitializeClient() {
        Logger reflectionsLogger = Logger.getLogger(Reflections.class.getName());
        reflectionsLogger.setLevel(Level.OFF);

        Reflections reflections = new Reflections("net.neoforged");
        Set<Class<?>> deletedClasses = reflections.get(Scanners.TypesAnnotated.with(Delete.class).asClass());

        for (Class<?> clazz : deletedClasses) {}

        NeoForgeConfigRegistry.INSTANCE.register(MOD_ID, ModConfig.Type.CLIENT, FeatureConfig.SPEC);
        container = ModLoader.createModContainer(MOD_ID);
        IEventBus eventBus = container.getModEventBus();
        eventBus.register(ComputeShaderPrograms.class);
        eventBus.register(OrientationCullingPrograms.class);
        conditionalInitialize(container.getModEventBus());
    }

    public void conditionalInitialize(IEventBus modEventBus) {
        //intentionally empty
    }
}
