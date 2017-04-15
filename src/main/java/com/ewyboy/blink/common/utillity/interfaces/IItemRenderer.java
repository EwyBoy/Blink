package com.ewyboy.blink.common.utillity.interfaces;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by EwyBoy
 **/
public interface IItemRenderer {

    @SideOnly(Side.CLIENT)
    void registerItemRenderer();

    int[] modelMetas();
}
