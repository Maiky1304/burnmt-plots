package dev.maiky.burnmtplots.modules.plots;

import co.aikar.commands.BukkitCommandManager;
import dev.maiky.burnmtplots.Main;
import dev.maiky.burnmtplots.modules.plots.commands.PlotCommand;
import me.lucko.helper.terminable.TerminableConsumer;
import me.lucko.helper.terminable.module.TerminableModule;

import javax.annotation.Nonnull;

public class PlotsModule implements TerminableModule {

    @Override
    public void setup(@Nonnull TerminableConsumer consumer) {
        BukkitCommandManager commandManager = Main.getInstance().getManager();
        commandManager.registerCommand(new PlotCommand());
    }

}
