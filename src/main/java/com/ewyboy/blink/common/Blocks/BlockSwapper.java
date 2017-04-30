package com.ewyboy.blink.common.blocks;

import com.ewyboy.blink.common.tiles.TileSwapper;
import com.ewyboy.blink.common.utillity.helpers.ParticleEngineHelper;
import com.ewyboy.blink.common.utillity.helpers.PlayerUtils;
import com.ewyboy.blink.common.utillity.helpers.SoundHelper;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

import static com.ewyboy.blink.common.utillity.helpers.PositionHelper.setTargetPos;

/**
 * Created by EwyBoy
 */
public class BlockSwapper extends BlockBaseModeled implements ITileEntityProvider {

    public BlockSwapper(Material material) {
        super(material);
        setHardness(3.0f);
        setLightLevel(0.2f);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos) {
        return MinecraftForgeClient.getRenderLayer() == BlockRenderLayer.TRANSLUCENT ? 0xF000F0 : super.getPackedLightmapCoords(state, source, pos);
    }

    @Override
    public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
         return layer == BlockRenderLayer.SOLID || layer == BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        IBlockState selectedBlock = world.getBlockState(pos.up());
        BlockPos selectedBlockPos = pos.up();

        selectedBlockPos = setTargetPos(selectedBlockPos, facing, 2);
        IBlockState targetBlock = world.getBlockState(selectedBlockPos);

        if (world.getBlockState(selectedBlockPos.down()).getBlock().equals(this)) {
            SoundHelper.playTeleportSound(world, selectedBlockPos);
            world.setBlockState(pos.up(), targetBlock);
            world.setBlockState(selectedBlockPos, selectedBlock);
            ParticleEngineHelper.spawnHelixEffect(world, pos.up().getX(), pos.up().getY(), pos.up().getZ(), EnumParticleTypes.CRIT_MAGIC, 0.4);
            ParticleEngineHelper.spawnHelixEffect(world, selectedBlockPos.getX(), selectedBlockPos.getY(), selectedBlockPos.getZ(), EnumParticleTypes.CRIT_MAGIC, 0.4);
        }

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
                    if (PlayerUtils.canPlayerFit(world, targetPos)) {
                        SoundHelper.playTeleportSound(world, targetPos);
                        ParticleEngineHelper.spawnHelixEffect(world, targetPos.getX(), targetPos.getY(), targetPos.getZ(), EnumParticleTypes.CRIT_MAGIC, 10);
                        ParticleEngineHelper.spawnHelixEffect(world, targetPos.getX(), targetPos.getY(), targetPos.getZ(), EnumParticleTypes.CRIT_MAGIC, 10);
                        player.setPositionAndUpdate(targetPos.getX() + 0.5, targetPos.getY() + 0.25, targetPos.getZ() + 0.5);
                    }
                }
            }
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileSwapper();
    }
}
