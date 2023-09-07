package com.github.meelock.creindust;

import com.github.meelock.creindust.blockentities.SteamTurbineBlockEntity;
import com.github.meelock.creindust.blocks.SteamTurbineBlock;
import com.github.meelock.creindust.config.CreIndustConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreIndust implements ModInitializer {
    /**
     * Logger for all logging.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger("creindust");
    /**
     * The only block in the mod, the fluid steam turbine.
     */
    public static final Block STEAM_TURBINE_BLOCK = new SteamTurbineBlock(FabricBlockSettings.create().strength(4.0f).requiresTool());

    public static final BlockEntityType<SteamTurbineBlockEntity> STEAM_TURBINE_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(SteamTurbineBlockEntity::new, STEAM_TURBINE_BLOCK).build();

    @Override
    public void onInitialize() {

        CreIndustConfig.init();

        LOGGER.info("Hello create and industrial world!");
        Registry.register(Registries.BLOCK, id("steam_turbine"), STEAM_TURBINE_BLOCK);
        Registry.register(Registries.ITEM, id("steam_turbine"), new BlockItem(STEAM_TURBINE_BLOCK, new FabricItemSettings()));
		Registry.register(Registries.BLOCK_ENTITY_TYPE, id("steam_turbine"), STEAM_TURBINE_BLOCK_ENTITY);
        FluidStorage.SIDED.registerForBlockEntity((mySteamT, direction) -> mySteamT.fluidStorage, STEAM_TURBINE_BLOCK_ENTITY);
    }

    public static Identifier id(String path) {
        return new Identifier("creindust", path);
    }
}
