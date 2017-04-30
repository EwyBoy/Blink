package com.ewyboy.blink.common.items;

import com.ewyboy.blink.common.utillity.helpers.ParticleEngineHelper;
import com.ewyboy.blink.common.utillity.helpers.PlayerUtils;
import com.ewyboy.blink.common.utillity.helpers.PositionHelper;
import com.ewyboy.blink.common.utillity.helpers.SoundHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
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
        int range;
        range = player.isSprinting() ? 2 : 1;

        BlockPos targetPos = PositionHelper.setTargetPos(pos, side, range);

        if (PlayerUtils.canPlayerFit(world, targetPos)) {
            player.setPositionAndUpdate(targetPos.getX() + 0.5, targetPos.getY() + 0.25, targetPos.getZ() + 0.5);
            SoundHelper.playTeleportSound(world, targetPos);
            ParticleEngineHelper.spawnHelixEffect(world, targetPos.getX(), side == EnumFacing.DOWN ? targetPos.getY() : targetPos.down().getY(), targetPos.getZ(), EnumParticleTypes.PORTAL, 1);
            return EnumActionResult.SUCCESS;
        } else {
            return EnumActionResult.FAIL;
        }
    }
}
