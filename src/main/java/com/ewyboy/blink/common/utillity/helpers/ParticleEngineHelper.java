package com.ewyboy.blink.common.utillity.helpers;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

/**
 * Created by EwyBoy
 */
public class ParticleEngineHelper {

    public static void spawnParticle(World world, double x, double y, double z, EnumParticleTypes particle, double velocityX, double velocityY, double velocityZ) {
        world.spawnParticle(particle, x + 0.5, y, z + 0.5, velocityX, velocityY, velocityZ);
    }

    public static void spawnHelixEffect(World world, int x, int y, int z, EnumParticleTypes particle, double duration) {
        for(double i = 0; i <= Math.PI; i += duration / 100) {
            double adjustedX = i * Math.cos(i) / 2, adjustedZ = i * Math.sin(i) / 2;
            helixPattern(world, particle, x, y, z, i, adjustedX, adjustedZ);
        }
    }

    private static void helixPattern(World world, EnumParticleTypes particle, double x, double y, double z, double i, double adjustedX, double adjustedZ) {
        world.spawnParticle(particle, x + 0.5, y, z + 0.5, adjustedX, i, adjustedZ);
        world.spawnParticle(particle, x + 0.5, y, z + 0.5, adjustedX * (-1), i, adjustedZ * (-1));
        world.spawnParticle(particle, x + 0.5, y, z + 0.5, adjustedX * (-1), i, adjustedZ);
        world.spawnParticle(particle, x + 0.5, y, z + 0.5, adjustedX, i, adjustedZ * (-1));
        world.spawnParticle(particle, x + 0.5, y, z + 0.5, adjustedZ, i, adjustedX);
        world.spawnParticle(particle, x + 0.5, y, z + 0.5, adjustedZ * (-1), i, adjustedX * (-1));
        world.spawnParticle(particle, x + 0.5, y, z + 0.5, adjustedZ * (-1), i, adjustedX);
        world.spawnParticle(particle, x + 0.5, y, z + 0.5, adjustedZ, i, adjustedX * (-1));
    }
}
