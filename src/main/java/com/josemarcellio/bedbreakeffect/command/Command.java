package com.josemarcellio.bedbreakeffect.command;

import com.josemarcellio.bedbreakeffect.BedBreakEffect;
import com.josemarcellio.bedbreakeffect.configuration.Messages;
import com.josemarcellio.bedbreakeffect.gui.BedBreakEffectGUI;
import com.josemarcellio.bedbreakeffect.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command {

    private final BedBreakEffect plugin;

    public Command(BedBreakEffect plugin) {
        this.plugin = plugin;
    }

    @BedBreakEffectCommand(command = "/bedwarsbedbreakeffect")
    public void main(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage ( Utils.getColor( Messages.MAIN_COMMAND ) );
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase ( "open" )) {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    BedBreakEffectGUI gui = new BedBreakEffectGUI ( plugin );
                    gui.open ( p );
                } else {
                    sender.sendMessage ( Utils.getColor( Messages.ONLY_PLAYER_CAN_USE_COMMAND ) );
                }
            }
        }
    }

    @BedBreakEffectCommand (command = "/bedwarsbedbreakeffectadmin", permission = "bwbbe.admin")
    public void admin(CommandSender sender, String[] args) {
        if (args.length == 0 || args.length == 1) {
            sender.sendMessage ( Utils.getColor ( Messages.ADMIN_COMMAND ) );
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase ( "open" )) {
                Player p = Bukkit.getPlayer(args[1]);
                BedBreakEffectGUI gui = new BedBreakEffectGUI ( plugin );
                if (p != null) {
                    gui.open ( p );
                }
            }
        }
    }
}
