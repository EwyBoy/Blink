package com.ewyboy.blink.common.utillity.helpers;

import net.minecraft.util.ResourceLocation;

import static com.ewyboy.blink.common.Reference.ModInfo.MOD_ID;

/**
 * Created by EwyBoy
 **/
public class ResourceLocationHelper {

    public static ResourceLocation createResourceLocation(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static ResourceLocation createGuiResourceLocation(String path) {
        return ResourceLocationHelper.createResourceLocation("textures" + "/" + "gui" + "/" + path);
    }

    public static ResourceLocation createBlockResourceLocation(String path) {
        return ResourceLocationHelper.createResourceLocation("blocks" + "/" + path);
    }

}
