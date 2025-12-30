package org.lilbrocodes.lemon_pie.client;

import net.fabricmc.api.ClientModInitializer;
import org.lilbrocodes.lemon_pie.config.ModConfig;

public class LemonPieClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModConfig.initializeClient();
    }
}
