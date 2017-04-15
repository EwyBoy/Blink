package com.ewyboy.blink.common.loaders;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import static com.ewyboy.blink.common.Reference.ModInfo.MOD_ID;

/**
 * Created by EwyBoy
 **/
public class CreativeTabLoader {

    public static CreativeTabs tabBlink = new CreativeTabs(MOD_ID) {
        public ItemStack getIconItemStack() {
            return new ItemStack(Blocks.ANVIL);
        }
        @Override
        public ItemStack getTabIconItem() {return null;}
    };
}
