package com.ewyboy.blink.proxy;

import com.ewyboy.blink.common.loaders.*;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by EwyBoy
 **/
public class CommonProxy {

    public static FMLEventChannel packetHandler;

    public Side getSide(){return Side.SERVER;}

    public void preInit(FMLPreInitializationEvent event) {
        ConfigLoader.registerConfig(event.getSuggestedConfigurationFile());
        BlockLoader.init();
        ItemLoader.init();
        TileEntityLoader.init();
        RecipeLoader.registerRecipes();
    }

    public void init(FMLInitializationEvent event) {}

    public void postInit(FMLPostInitializationEvent event){}

}
