package dev.maiky.burnmtplots.modules.plots.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandIssuer;
import co.aikar.commands.RegisteredCommand;
import co.aikar.commands.annotation.*;
import dev.maiky.burnmtplots.Main;
import me.lucko.helper.text3.Text;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@CommandAlias("plot")
@Description("Het plot commando van de BurnMT plot plugin")
public class PlotCommand extends BaseCommand {

    @Subcommand("addowner")
    @Syntax("<speler> [plot]")
    @Description("Voeg een speler toe als owner van een plot")
    @CommandCompletion("@players")
    public void addOwner(Player player, String target, @Default String plot) {
        legacyMinetopiaCommand(player, "addowner", target, plot);
    }

    @Subcommand("removeowner")
    @Syntax("<speler> [plot]")
    @Description("Verwijder een speler als owner van een plot")
    @CommandCompletion("@players")
    public void removeOwner(Player player, String target, @Default String plot) {
        legacyMinetopiaCommand(player, "removeowner", target, plot);
    }

    @Subcommand("addmember")
    @Syntax("<speler> [plot]")
    @Description("Voeg een speler toe als member van een plot")
    @CommandCompletion("@players")
    public void addMember(Player player, String target, @Default String plot) {
        legacyMinetopiaCommand(player, "addmember", target, plot);
    }

    @Subcommand("removemember")
    @Syntax("<speler> [plot]")
    @Description("Verwijder een speler als member van een plot")
    @CommandCompletion("@players")
    public void removeMember(Player player, String target, @Default String plot) {
        legacyMinetopiaCommand(player, "removemember", target, plot);
    }

    @Subcommand("create")
    @Syntax("<plotnaam> <vert>")
    @Description("Maak een plot aan")
    @CommandPermission("minetopiasdb.plot.create")
    public void create(Player player, String plotName, @Default("false") boolean vert) {
        legacyMinetopiaCommand(player, "create", plotName, String.valueOf(vert));
    }

    @Subcommand("delete")
    @Syntax("<plotnaam>")
    @Description("Verwijder een plot")
    public void delete(Player player, String plotName) {
        legacyMinetopiaCommand(player, "delete", plotName);
    }

    @Subcommand("tp")
    @Description("Teleporteer naar een plot")
    @Syntax("<plot>")
    public void tp(Player player, String plotName) {
        legacyMinetopiaCommand(player, "tp", plotName);
    }

    @Subcommand("clear")
    @Syntax("<plot>")
    @Description("Clear een plot")
    @CommandPermission("minetopiasdb.plot.clear")
    public void clear(Player player, String plotName) {
        legacyMinetopiaCommand(player, "clear", plotName);
    }

    @Subcommand("inactive")
    @Description("Bekijk alle inactieve plots")
    public void inactive(Player player) {
        legacyMinetopiaCommand(player, "inactive");
    }

    @Subcommand("bereken")
    @Description("Bereken de waarde van een plot")
    @Syntax("<plot>")
    @CommandPermission("minetopiasdb.plot.calculate")
    public void bereken(Player player, String plot) {
        legacyMinetopiaCommand(player, "bereken", plot);
    }

    @Subcommand("list")
    @Description("Bekijk een lijst met plots")
    public void list(Player player) {
        legacyMinetopiaCommand(player, "list");
    }

    @Subcommand("setbeschrijving")
    @Description("Verander de beschrijving van een plot")
    @Syntax("<plot> <description>")
    public void description(Player player, String plotName, String description) {
        legacyMinetopiaCommand(player, "setbeschrijving", plotName, description);
    }

    @Subcommand("setlevels")
    @Description("Verander de levels die je krijgt van een plot")
    @Syntax("<plot> <level>")
    public void levels(Player player, String plotName, int levels) {
        legacyMinetopiaCommand(player, "setlevels", plotName, String.valueOf(levels));
    }

    @Subcommand("plist")
    @Description("Bekijk plots van een speler")
    @Syntax("<speler>")
    public void pList(Player player, String target) {
        legacyMinetopiaCommand(player, "plist", target);
    }

    @SuppressWarnings("all")
    @Default
    @HelpCommand
    public void help(CommandIssuer issuer, @Default("1") int page) {
        issuer.sendMessage(Text.colorize("&6&lBurnMT Plots &7v" + Main.getInstance().getDescription().getVersion()));
        issuer.sendMessage(" ");
        List<String> lines = new ArrayList<>();
        for (String subCommand : getSubCommands().keys()) {
            RegisteredCommand rc = getSubCommands().get(subCommand).iterator().next();
            if (rc.getHelpText() == null || rc.getHelpText().length()==1 || rc.getHelpText().length()==0)
                continue;
            if (rc.getRequiredPermissions().size() != 0) {
                boolean failed = false;
                for (Object s : rc.getRequiredPermissions()) {
                    if (!issuer.hasPermission(s.toString())) {
                        failed = true;
                    }
                }
                if (failed) continue;
            }

            lines.add(String.format(ChatColor.translateAlternateColorCodes('&', "&7- &b/%s &3%s &b%s&f- &b%s"), this.getExecCommandLabel(),
                    rc.getPrefSubCommand().equals(" ") ? "" : rc.getPrefSubCommand(),
                    rc.getSyntaxText().equals(" ") ? "" : rc.getSyntaxText() + (rc.getHelpText().equals(" ") ? "" : " "),
                    rc.getHelpText().equals(" ") ? "" : rc.getHelpText()));
        }

        page = page - 1;

        int pages = lines.size() / 5;
        int start = page * 5;
        int end = start + 5;

        if (page > pages) {
            issuer.sendMessage("§cDeze pagina bestaat niet.");
            return;
        }

        for (int i = start; i < end; i++) {
            try {
                issuer.sendMessage(lines.get(i));
            } catch (IndexOutOfBoundsException exception) {
                break;
            }
        }
        if (page < pages) issuer.sendMessage(Text.colorize("&7Gebruik &9/" + getExecCommandLabel() + " " + (page + 2) + " &7om naar de volgende pagina te gaan."));
    }

    private void legacyMinetopiaCommand(Player player, String... args) {
        player.performCommand("minetopiasdb:plot " + StringUtils.join(args, ' '));
    }

}
