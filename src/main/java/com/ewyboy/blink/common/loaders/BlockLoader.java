package com.ewyboy.blink.common.loaders;

import com.ewyboy.blink.common.Blocks.BlockBase;
import com.ewyboy.blink.common.Blocks.BlockSwapper;
import com.ewyboy.blink.common.utillity.interfaces.IBlockRenderer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;

import static com.ewyboy.blink.common.Reference.ModInfo.MOD_ID;

/**
 * Created by EwyBoy
 **/
public class BlockLoader {

    public static final HashMap<String, Block> BLOCKS = new HashMap<>();

    //Blocks
    public static final BlockBase testBlock = new BlockBase(Material.DRAGON_EGG);
    public static final BlockSwapper swapper = new BlockSwapper(Material.DRAGON_EGG);

    public static void init() {
        registerBlocks();
        initModels();
        initItemModels();
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        BLOCKS.values().stream().filter(block -> block instanceof IBlockRenderer).forEachOrdered(block -> ((IBlockRenderer) block).registerBlockRenderer());
    }

    @SideOnly(Side.CLIENT)
    public static void initItemModels() {
        BLOCKS.values().stream().filter(block -> block instanceof IBlockRenderer).forEachOrdered(block -> ((IBlockRenderer) block).registerBlockItemRenderer());
    }

    private static void registerBlocks() {
        try {
            for (Field field : BlockLoader.class.getDeclaredFields()) {
                Object obj = field.get(null);
                if (obj instanceof Block) {
                    Block block = (Block) obj;
                    String name =  "block" + field.getName().toLowerCase(Locale.ENGLISH);
                    registerBlock(block, name);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static void registerBlock(Block block, String name) {
        GameRegistry.register(block.setRegistryName(MOD_ID, name).setUnlocalizedName(MOD_ID + "." + name));
        BLOCKS.put(block.getRegistryName().toString(), block);
        ItemBlock item;
        item = block instanceof IHasCustomItem ? ((IHasCustomItem) block).getItemBlock() : new ItemBlock(block);
        GameRegistry.register((ItemBlock) item.setRegistryName(MOD_ID, name).setUnlocalizedName(MOD_ID + "." + name));
    }


    public interface IHasCustomItem {
        ItemBlock getItemBlock();
    }
}