package com.ewyboy.blink.common.Blocks;

import com.ewyboy.blink.common.Logger;
import com.ewyboy.blink.common.utillity.helpers.ParticleEngineHelper;
import com.ewyboy.blink.common.utillity.helpers.PlayerUtils;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by EwyBoy
 */
public class BlockSwapper extends BlockBaseModeled {

    public BlockSwapper(Material material) {
        super(material);
        setHardness(3.0f);
    }

    public static BlockPos setTargetPos(BlockPos targetPos, EnumFacing facing, int range) {
        switch (facing) {
            case NORTH: targetPos = new BlockPos(targetPos.add(0,0,range)); break;
            case SOUTH: targetPos = new BlockPos(targetPos.add(0,0,-range)); break;
            case EAST: targetPos = new BlockPos(targetPos.add(-range,0,0)); break;
            case WEST: targetPos = new BlockPos(targetPos.add(range,0,0)); break;

            default: targetPos = new BlockPos(targetPos.add(0,0,0)); break;
        }
        return targetPos;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        Logger.info(facing);
        IBlockState selectedBlock = world.getBlockState(pos.up());
        BlockPos selectedBlockPos = pos.up();

        selectedBlockPos = setTargetPos(selectedBlockPos, facing, 2);
        IBlockState targetBlock = world.getBlockState(selectedBlockPos);

        world.setBlockState(pos.up(), targetBlock);
        world.setBlockState(selectedBlockPos, selectedBlock);

        ParticleEngineHelper.spawnHelixEffect(world, selectedBlockPos.getX(), selectedBlockPos.getY(), selectedBlockPos.getZ(), EnumParticleTypes.CRIT_MAGIC, 0.4);

        return true;
    }

    @Override
    public void onEntityWalk(World world, BlockPos pos, Entity entity) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            EnumFacing facing = PlayerUtils.getPlayerFacing(player);
            BlockPos targetPos = pos;
            entity.setSneaking(true);

            targetPos = setTargetPos(targetPos.up(), facing, 2);

            if (world.getBlockState(targetPos.down()).getBlock().equals(this)) {
                ParticleEngineHelper.spawnParticle(world, targetPos.getX(), targetPos.getY(), targetPos.getZ(), EnumParticleTypes.CRIT_MAGIC, 0f,0.35f,0f);

                if (entity.isSneaking()) {
                    ParticleEngineHelper.spawnHelixEffect(world, targetPos.getX(), targetPos.getY(), targetPos.getZ(), EnumParticleTypes.CRIT_MAGIC, 10);
                    ParticleEngineHelper.spawnHelixEffect(world, targetPos.getX(), targetPos.getY(), targetPos.getZ(), EnumParticleTypes.CRIT_MAGIC, 10);
                    player.setPositionAndUpdate(targetPos.getX() + 0.5, targetPos.getY() + 0.25, targetPos.getZ() + 0.5);
                }
            }
        }
    }
}
