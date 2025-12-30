package org.lilbrocodes.lemon_pie;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class LemonPie implements ModInitializer {
    private static final String MOD_ID = "lemon_pie";

    @Override
    public void onInitialize() {

    }

    public static Identifier identify(String path) {
        return Identifier.of(MOD_ID, path);
    }
}
