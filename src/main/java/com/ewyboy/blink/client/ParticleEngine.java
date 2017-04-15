package com.ewyboy.blink.client;

import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;

/**
 * Created by EwyBoy
 */
public class ParticleEngine extends Particle {

    public ParticleEngine(World world, double posX, double posY, double posZ) {
        super(world, posX, posY, posZ);
    }
}
