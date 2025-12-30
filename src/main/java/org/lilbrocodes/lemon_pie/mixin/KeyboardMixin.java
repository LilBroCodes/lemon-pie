package org.lilbrocodes.lemon_pie.mixin;

import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.lilbrocodes.lemon_pie.config.ModConfig;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "onKey", at = @At("HEAD"))
    private void lemonPie$handleProfilerLetters(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        if (ModConfig.client().debugPieLettering.get()) {
            if (client == null || client.currentScreen != null) return;
            if (action != GLFW.GLFW_PRESS) return;
            if (!client.options.debugProfilerEnabled) return;

            if (key >= GLFW.GLFW_KEY_A && key <= GLFW.GLFW_KEY_Z) {
                int letterIndex = key - GLFW.GLFW_KEY_A;
                int digit = letterIndex + 10;
                client.handleProfilerKeyPress(digit);
            }
        }
    }
}
