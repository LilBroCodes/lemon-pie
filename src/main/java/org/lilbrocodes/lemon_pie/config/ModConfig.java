package org.lilbrocodes.lemon_pie.config;

import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.fzzyhmstrs.fzzy_config.api.FileType;
import me.fzzyhmstrs.fzzy_config.api.RegisterType;
import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedBoolean;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedFloat;
import org.jetbrains.annotations.NotNull;
import org.lilbrocodes.lemon_pie.LemonPie;

public class ModConfig {
    private static Client CLIENT;

    public static Client client() {
        if (CLIENT == null) CLIENT = ConfigApiJava.registerAndLoadConfig(Client::new, RegisterType.CLIENT);
        return CLIENT;
    }

    @SuppressWarnings("CanBeFinal")
    public static class Client extends Config {
        private Client() {
            super(LemonPie.identify("client"));
        }

        public ValidatedFloat debugPieScale = new ValidatedFloat(1f, 10f, 0.1f);

        @Prefix("If enabled, profiler entries above 9 can be chosen with letters of the alphabet starting from a.")
        public ValidatedBoolean debugPieLettering = new ValidatedBoolean(true);

        @Override
        public int defaultPermLevel() {
            return 0;
        }

        @Override
        public @NotNull FileType fileType() {
            return FileType.JSONC;
        }
    }

    public static void initializeClient() {
        if (CLIENT == null) CLIENT = ConfigApiJava.registerAndLoadConfig(Client::new, RegisterType.CLIENT);
    }
}
