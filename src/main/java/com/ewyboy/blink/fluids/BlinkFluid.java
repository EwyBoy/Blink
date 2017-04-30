package com.ewyboy.blink.fluids;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import static com.ewyboy.blink.common.Reference.ModInfo.*;

/**
 * Created by EwyBoy
 */
public class BlinkFluid extends Fluid {

    public BlinkFluid(String fluidName, int viscosity, int density, int luminosity) {
        super(fluidName,
                new ResourceLocation(MOD_ID + ":" + "blocks" + "/" + fluidName + "_still"),
                new ResourceLocation(MOD_ID + ":" + "blocks" + "/" + fluidName + "_flow")
        );
        this.setViscosity(viscosity);
        this.setDensity(density);
        this.setLuminosity(luminosity);
        FluidRegistry.registerFluid(this);
    }
}
