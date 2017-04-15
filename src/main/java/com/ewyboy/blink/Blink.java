package com.ewyboy.blink;

import com.ewyboy.blink.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static com.ewyboy.blink.common.Reference.ModInfo.*;
import static com.ewyboy.blink.common.Reference.Paths.CLIENT_PROXY;
import static com.ewyboy.blink.common.Reference.Paths.COMMON_PROXY;

@Mod(modid = MOD_ID, name = MOD_NAME, version = VERSION)
public class Blink {

    @Mod.Instance(MOD_ID)
    public static Blink INSTANCE;

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
