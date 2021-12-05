package dev.maiky.burnmtplots;

import co.aikar.commands.BukkitCommandManager;
import dev.maiky.burnmtplots.modules.data.DataModule;
import dev.maiky.burnmtplots.modules.plots.PlotsModule;
import lombok.Getter;
import me.lucko.helper.plugin.ExtendedJavaPlugin;

public final class Main extends ExtendedJavaPlugin {

    @Getter
    private static Main instance;

    @Getter
    private BukkitCommandManager manager;

    @Override
    protected void enable() {
        // Instantiate Java Plugin
        instance = this;

        // Instantiate command manager
        this.manager = new BukkitCommandManager(this);

        // Load modules
        bindModule(new DataModule());
        bindModule(new PlotsModule());
    }

}
