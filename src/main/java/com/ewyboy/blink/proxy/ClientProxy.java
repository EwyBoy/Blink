package com.ewyboy.blink.proxy;

import com.ewyboy.blink.common.Items.ItemBase;
import com.ewyboy.blink.common.loaders.BlockLoader;
import com.ewyboy.blink.common.loaders.ItemLoader;
import com.ewyboy.blink.common.utillity.interfaces.IBlockRenderer;
import com.ewyboy.blink.common.utillity.interfaces.IItemRenderer;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

import static com.ewyboy.blink.common.Reference.ModInfo.MOD_ID;

/**
 * Created by EwyBoy
 **/
public class ClientProxy extends CommonProxy {

    @Override
    public Side getSide() {
        return Side.CLIENT;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        initBlockModels();
        initItemModels();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    private void initItemModels() {
        ItemLoader.ITEMS.values().stream().filter(item -> item instanceof IItemRenderer).forEachOrdered(item -> {
            for (int i : ((IItemRenderer) item).modelMetas()) {
                ModelBakery.registerItemVariants(item, new ResourceLocation(MOD_ID + ":" + ((ItemBase) item).itemName(i)));
                ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(MOD_ID + ":" + ((ItemBase) item).itemName(i), "inventory"));
            }
        });
    }

    private void initBlockModels() {
        BlockLoader.BLOCKS.values().stream().filter(block -> block instanceof IBlockRenderer).forEachOrdered(block -> {
            for (int i : ((IBlockRenderer) block).modelMetas()) {
                ModelBakery.registerItemVariants(Item.getItemFromBlock(block), block.getRegistryName());
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), i, new ModelResourceLocation(block.getRegistryName(), "inventory"));
            }
        });
    }
}
