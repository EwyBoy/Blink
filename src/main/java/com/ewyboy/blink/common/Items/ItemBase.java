package com.ewyboy.blink.common.Items;

import com.ewyboy.blink.common.loaders.CreativeTabLoader;
import com.ewyboy.blink.common.utillity.interfaces.IItemRenderer;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by EwyBoy
 */
public class ItemBase extends Item implements IItemRenderer {

    public ItemBase(String name) {
        setCreativeTab(CreativeTabLoader.tabBlink);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerItemRenderer() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public int[] modelMetas() {
        return new int[0];
    }

    public String itemName(int meta) {
        return null;
    }
}