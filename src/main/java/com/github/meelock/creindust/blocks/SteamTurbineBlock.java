package com.github.meelock.creindust.blocks;

import com.github.meelock.creindust.blockentities.SteamTurbineBlockEntity;
import com.simibubi.create.content.kinetics.base.DirectionalKineticBlock;
import com.simibubi.create.foundation.block.IBE;
import io.github.fabricators_of_create.porting_lib.block.ConnectableRedstoneBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class SteamTurbineBlock extends DirectionalKineticBlock implements IBE<SteamTurbineBlockEntity>, ConnectableRedstoneBlock {
    public SteamTurbineBlock(Settings settings) {
        super(settings);
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            player.sendMessage(Text.of("Hello, world!"), false);
        }

        return ActionResult.SUCCESS;
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 1.0f, 0.5f);
    }

    /**
     * @param state
     * @return
     */
    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public Class<SteamTurbineBlockEntity> getBlockEntityClass() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public BlockEntityType<? extends SteamTurbineBlockEntity> getBlockEntityType() {
        return null;
    }

    /**
     * @param state
     * @param world
     * @param pos
     * @param side
     * @return
     */
    @Override
    public boolean canConnectRedstone(BlockState state, BlockView world, BlockPos pos, Direction side) {
        return false;
    }
}
