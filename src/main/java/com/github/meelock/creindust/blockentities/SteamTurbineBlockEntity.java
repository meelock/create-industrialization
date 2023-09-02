package com.github.meelock.creindust.blockentities;

import com.github.meelock.creindust.CreIndust;
import com.simibubi.create.content.kinetics.base.GeneratingKineticBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollValueBehaviour;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class SteamTurbineBlockEntity extends GeneratingKineticBlockEntity {
    protected ScrollValueBehaviour generatedSpeed;
    private boolean cc_update_rpm = false;
    private int cc_new_rpm = 32;
    public final SingleVariantStorage<FluidVariant> fluidStorage = new SingleVariantStorage<>() {
        @Override
        protected FluidVariant getBlankVariant() {
            return FluidVariant.blank();
        }

        @Override
        protected long getCapacity(FluidVariant variant) {
            // Here, you can pick your capacity depending on the fluid variant.
            // For example, if we want to store 8 buckets of any fluid:
            return (FluidConstants.BUCKET) / 81; // This will convert it to mB amount to be consistent;
        }

        @Override
        protected void onFinalCommit() {
            // Called after a successful insertion or extraction, markDirty to ensure the new amount and variant will be saved properly.
            markDirty();
        }
    };
    public SteamTurbineBlockEntity(BlockPos pos, BlockState state) {
        super(CreIndust.STEAM_TURBINE_BLOCK_ENTITY, pos, state);
    }

    /**
     * @param compound
     * @param clientPacket
     */
    @Override
    protected void write(NbtCompound compound, boolean clientPacket) {
        compound.put("fluidVariant", fluidStorage.variant.toNbt());
        compound.putLong("amount", fluidStorage.amount);
        super.write(compound, clientPacket);
    }

    /**
     * @param compound
     * @param clientPacket
     */
    @Override
    protected void read(NbtCompound compound, boolean clientPacket) {
        super.read(compound, clientPacket);
        fluidStorage.variant = FluidVariant.fromNbt(compound.getCompound("fluidVariant"));
        fluidStorage.amount = compound.getLong("amount");
    }

}
