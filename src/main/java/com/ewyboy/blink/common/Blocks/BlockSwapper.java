package com.ewyboy.blink.common.Blocks;

import com.ewyboy.blink.common.Logger;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

/**
 * Created by EwyBoy
 */
public class BlockSwapper extends BlockBaseModeled {

    public BlockSwapper(Material material) {
        super(material);
        setHardness(3.0f);
    }

   /* @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entity) {
        if (entity instanceof EntityPlayer) {

            EntityPlayer player = (EntityPlayer) entity;

            int playerLooking = MathHelper.floor((double) ((player.rotationYaw * 4f) / 360f) + 0.5D) & 3;
            Logger.info(playerLooking);

            BlockPos targetPos = new BlockPos(pos);

            switch (playerLooking) {
                case 0: targetPos.add(0,0, 2);
                case 1: targetPos.add(-2,0, 0);
                case 2: targetPos.add(0,0, -2);
                case 3: targetPos.add(2,0, 0);

                default: targetPos.add(0,0,0);
            }

            if (player.isSneaking()) {
                player.setPositionAndUpdate(targetPos.getX(), targetPos.getY() + 0.5D, targetPos.getZ());

            }
        }
    }*/

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entity) {
        if (entity instanceof EntityPlayer) {

            EntityPlayer player = (EntityPlayer) entity;

            int playerLooking = MathHelper.floor((double) ((player.rotationYaw * 4f) / 360f) + 0.5D) & 3;
            Logger.info(playerLooking);

            BlockPos targetPos = new BlockPos(pos);

            switch (playerLooking) {
                case 0: targetPos.add(0,0, 2);
                case 1: targetPos.add(-2,0, 0);
                case 2: targetPos.add(0,0, -2);
                case 3: targetPos.add(2,0, 0);

                default: targetPos.add(0,0,0);
            }

            if (player.isSneaking()) {
                player.setPositionAndUpdate(targetPos.getX(), targetPos.getY() + 0.5D, targetPos.getZ());

            }
        }
    }


    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        Logger.info("facing: " + facing);

        BlockPos target = new BlockPos(pos.up());

        switch (facing) {
            case UP:    target.add(0,2,0);
            case DOWN:  target.add(0,-2,0);
            case NORTH:  target.add(2,0,0);
            case SOUTH:  target.add(-2,0,0);
            case EAST:  target.add(0,0,2);
            case WEST:  target.add(0,0,-2);
        }

        IBlockState targetBlock = world.getBlockState(pos.up());
        world.setBlockToAir(pos.up());
        world.setBlockState(target, targetBlock);

        return false;
    }
}
