package dev.maiky.burnmtplots.modules.data;

import lombok.Getter;
import me.lucko.helper.Services;
import me.lucko.helper.sql.Sql;
import me.lucko.helper.sql.SqlProvider;
import me.lucko.helper.terminable.TerminableConsumer;
import me.lucko.helper.terminable.module.TerminableModule;

import javax.annotation.Nonnull;

public class DataModule implements TerminableModule {

    @Getter
    private static DataModule instance;

    @Getter
    private Sql sql;

    @Override
    public void setup(@Nonnull TerminableConsumer consumer) {
        // Instantiate instance variable
        instance = this;

        // Instantiate SQL variable
        SqlProvider provider = Services.load(SqlProvider.class);
        this.sql = provider.getSql();
    }

}
