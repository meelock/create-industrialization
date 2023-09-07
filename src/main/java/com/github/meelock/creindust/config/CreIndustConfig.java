package com.github.meelock.creindust.config;

import com.github.meelock.creindust.CreIndust;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class CreIndustConfig {
    public static CreIndustConfig INSTANCE;

    public static void init() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Path configFolder = FabricLoader.getInstance().getConfigDir();
        Path configFile = configFolder.resolve("creindust.json");
        CreIndustConfig instance;
        try (InputStream is = Files.newInputStream(configFile)) {
            instance = gson.fromJson(new InputStreamReader(is), CreIndustConfig.class);
        } catch (Exception e) {
            instance = new CreIndustConfig();
            instance.initValues();
            try (OutputStream os = Files.newOutputStream(configFile); Writer w = new OutputStreamWriter(os)) {
                gson.toJson(instance, w);
            } catch (IOException ex) {
                CreIndust.LOGGER.error("Credindust faild: ", ex);
            }
        }
        INSTANCE = instance;
    }

    public Set<String> burnable = new HashSet<>();

    private void initValues() {
        for (Identifier i : Registries.FLUID.getIds()) {
            if (i.getPath().contains("steam")) burnable.add(i.toString());
        }
    }
}
