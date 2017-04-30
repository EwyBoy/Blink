package com.ewyboy.blink.common.utillity.helpers;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

/**
 * Created by EwyBoy
 */
public class PositionHelper {

    public static BlockPos setTargetPos(BlockPos targetPos, EnumFacing facing, int range) {
        switch (facing) {
            case NORTH: targetPos = new BlockPos(targetPos.add(0,0,range)); break;
            case SOUTH: targetPos = new BlockPos(targetPos.add(0,0,-range)); break;
            case EAST: targetPos = new BlockPos(targetPos.add(-range,0,0)); break;
            case WEST: targetPos = new BlockPos(targetPos.add(range,0,0)); break;
            case UP: targetPos = new BlockPos(targetPos.add(0, -range-1, 0)); break;
            case DOWN: targetPos = new BlockPos(targetPos.add(0, range, 0)); break;

            default: targetPos = new BlockPos(targetPos.add(0,0,0)); break;
        }
        return targetPos;
    }
}
