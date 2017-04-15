package com.ewyboy.blink.common.Items;

import com.ewyboy.blink.common.Logger;
import com.ewyboy.blink.common.utillity.helpers.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by EwyBoy
 */
public class ItemBlitzer extends ItemBase {

    public ItemBlitzer(String name) {
        super(name);
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        if (!world.isRemote) {
            EnumFacing facing = PlayerUtils.getPlayerFacing(player);

            BlockPos targetPos = pos;

            switch (facing) {
                case NORTH: targetPos.add(5, 0, 0);
            }

            if (player.isSprinting()) {
                player.setPositionAndUpdate(targetPos.getX(), targetPos.getY(), targetPos.getZ());
            }

        }

        return EnumActionResult.FAIL;
    }
}
