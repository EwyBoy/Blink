package com.ewyboy.blink.common.loaders;

import com.ewyboy.blink.common.tiles.TileSwapper;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by EwyBoy
 */
public class TileEntityLoader {

    public static final HashMap<String, TileEntity> TILE_ENTITIES = new HashMap<>();

    public static TileSwapper swapper = new TileSwapper();

    public static void init() {
        registerTileEntity();
    }

    private static void registerTileEntity() {
        try {
            for (Field field : TileEntityLoader.class.getDeclaredFields()) {
                Object obj = field.get(null);
                if (obj instanceof TileEntity) {
                    TileEntity block = (TileEntity) obj;
                    String name = field.getName().toLowerCase(Locale.ENGLISH);
                    registerTileEntity(block, name);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static void registerTileEntity(TileEntity tileEntity, String name) {
        GameRegistry.registerTileEntity(tileEntity.getClass(), name);
        TILE_ENTITIES.put(tileEntity.toString(), tileEntity);
    }
}
