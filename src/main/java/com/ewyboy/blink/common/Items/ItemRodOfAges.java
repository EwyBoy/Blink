package com.ewyboy.blink.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by EwyBoy
 */
public class ItemRodOfAges extends ItemBase {

    public ItemStack item = new ItemStack(this);

    public ItemRodOfAges(String name) {
        super(name);
    }

    public static void storeBlockToPlayer(EntityPlayer player) {
        NBTTagCompound nbt = new NBTTagCompound();
        player.capabilities.readCapabilitiesFromNBT(nbt);
        player.capabilities.writeCapabilitiesToNBT(nbt);
    }

}
