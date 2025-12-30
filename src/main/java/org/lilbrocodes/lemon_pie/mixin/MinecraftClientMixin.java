package org.lilbrocodes.lemon_pie.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.profiler.ProfileResult;
import org.lilbrocodes.lemon_pie.config.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Inject(method = "drawProfilerResults", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;loadIdentity()V", shift = At.Shift.AFTER))
    public void lemonPie$makePieAbideByGuiScale(DrawContext context, ProfileResult profileResult, CallbackInfo ci, @Local MatrixStack matrices) {
        float guiScale = ModConfig.client().debugPieScale.get();
        float mv = (guiScale - 1) / 0.1f;

        matrices.translate(-195 * mv, -95 * mv, 0);
        matrices.scale(guiScale, guiScale, 1.0F);
    }

    @Redirect(method = "drawProfilerResults", at = @At(value = "INVOKE", target = "Ljava/lang/StringBuilder;toString()Ljava/lang/String;"))
    private String lemonPie$replaceProfilerIndex(StringBuilder instance) {
        if (ModConfig.client().debugPieLettering.get()) {
            String original = instance.toString();
            StringBuilder result = new StringBuilder();

            Pattern pattern = Pattern.compile("\\[(\\d{2,})]");
            Matcher matcher = pattern.matcher(original);

            while (matcher.find()) {
                int value = Integer.parseInt(matcher.group(1));
                char letter = (char) ('a' + (value - 10));
                matcher.appendReplacement(result, "[" + letter + "]");
            }

            matcher.appendTail(result);
            return result.toString();
        } else return instance.toString();
    }
}
