package com.example.modid;

import net.minecraft.client.audio.ISound;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = RandomPitchMod.MODID, name = RandomPitchMod.NAME, version = RandomPitchMod.VERSION)
public class RandomPitchMod {
    public static final String MODID = "sounddynamic";
    public static final String NAME = "Sound Dynamic";
    public static final String VERSION = "1.0";

    private static final Logger LOGGER = LogManager.getLogger();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onSoundPlayed(PlaySoundEvent event) {
        ISound sound = event.getSound();
        if (sound != null) {
            LOGGER.debug("Original sound: " + sound.getSoundLocation()); // Log the original sound location

            // Wrap the sound in the RandomPitchSound to modify the pitch
            ISound wrappedSound = new RandomPitchSound(sound);

            // Log the new sound's pitch
            LOGGER.debug("Modified sound pitch: " + wrappedSound.getPitch());

            // Use setResultSound to replace the sound with the wrapped sound
            event.setResultSound(wrappedSound);
        }
    }
}
