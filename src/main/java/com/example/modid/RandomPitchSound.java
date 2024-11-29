package com.example.modid;

import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.Sound;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import org.lwjgl.openal.AL;

import javax.annotation.Nullable;
import java.util.Random;

public class RandomPitchSound implements ISound {
    private final ISound originalSound;
    private final Random random = new Random();

    public RandomPitchSound(ISound originalSound) {
        this.originalSound = originalSound;
    }

    @Override
    public ResourceLocation getSoundLocation() {
        // Return the sound location directly from the original sound
        return originalSound.getSoundLocation();
    }

    @Override
    public Sound getSound() {
        return originalSound.getSound();
    }

    @Override
    public SoundCategory getCategory() {
        return originalSound.getCategory();
    }

    @Override
    public boolean canRepeat() {
        return originalSound.canRepeat();
    }

    @Override
    public int getRepeatDelay() {
        return originalSound.getRepeatDelay();
    }

    @Override
    public float getVolume() {
        if (getCategory() == SoundCategory.MUSIC) {
            return originalSound.getVolume();
        }
        // Randomize the volume within a range
        // Range: 0.8 to 1.8
        return 0.8f + (random.nextFloat() * 1);


    }

    @Override
    public float getPitch() {
        // Randomize the pitch between 0.8 and 1.2
        return 0.8f + (random.nextFloat() * 0.4f);
    }

    @Override
    public float getXPosF() {
        return originalSound.getXPosF();
    }

    @Override
    public float getYPosF() {
        return originalSound.getYPosF();
    }

    @Override
    public float getZPosF() {
        return originalSound.getZPosF();
    }

    @Override
    public AttenuationType getAttenuationType() {
        return originalSound.getAttenuationType();
    }

    @Nullable
    @Override
    public SoundEventAccessor createAccessor(SoundHandler handler) {
        // Pass the original accessor from the original sound
        return originalSound.createAccessor(handler);
    }
}
