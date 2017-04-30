package com.ewyboy.blink.common.loaders;

import com.ewyboy.blink.common.items.ItemBlitzer;
import com.ewyboy.blink.common.items.ItemRodOfAges;
import com.ewyboy.blink.common.utillity.interfaces.IItemRenderer;
import net.minecraft.item.Item;
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
public class ItemLoader {

    public static final HashMap<String, Item> ITEMS = new HashMap<>();

    /**     items       **/
    public static final ItemBlitzer blitzer = new ItemBlitzer("blitzer");
    public static final ItemRodOfAges rodOfAges = new ItemRodOfAges("rodOfAges");

    public static void init() {
        registerItems();
        initModels();
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        ItemLoader.ITEMS.values().stream().filter(item -> item instanceof IItemRenderer).forEachOrdered(item -> ((IItemRenderer) item).registerItemRenderer());
    }

    private static void registerItems() {
        try {
            for (Field field : ItemLoader.class.getDeclaredFields()) {
                Object obj = field.get(null);
                if (obj instanceof Item) {
                    Item item = (Item) obj;
                    String name = "item" + field.getName().toLowerCase(Locale.ENGLISH);
                    registerItem(item, name);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static void registerItem(Item item, String name) {
        String itemName = name.toLowerCase(Locale.ENGLISH);
        GameRegistry.register(item.setRegistryName(MOD_ID, itemName).setUnlocalizedName(MOD_ID + "." + itemName));
        ITEMS.put(item.getRegistryName().toString(), item);
    }
}
