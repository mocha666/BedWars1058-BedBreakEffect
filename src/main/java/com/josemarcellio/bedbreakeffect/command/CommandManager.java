package com.josemarcellio.bedbreakeffect.command;

import com.josemarcellio.bedbreakeffect.configuration.Messages;
import com.josemarcellio.bedbreakeffect.utils.Utils;
import org.bukkit.command.Command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandManager implements CommandExecutor {
    private final Plugin plugin;

    private final Map<String, RegisteredCommand> registeredCommandTable = new HashMap<>();

    public CommandManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void command(Object object) {
        for (Method method : object.getClass().getMethods()) {
            BedBreakEffectCommand annotation = method.getAnnotation(BedBreakEffectCommand.class);

            if (annotation != null) {

                String base = annotation.command().split(" ")[0].substring(1);
                PluginCommand pluginCommand = plugin.getServer().getPluginCommand(base);

                if (pluginCommand == null)
                    throw new RuntimeException(String.format("ERROR! can't register command '%s'", base));
                else {
                    pluginCommand.setExecutor(this);
                    registeredCommandTable.put(annotation.command().substring(1), new RegisteredCommand(method, object, annotation));
                }
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        StringBuilder sb = new StringBuilder();
        for (int i = -1; i <= args.length - 1; i++) {
            if (i == -1)
                sb.append(command.getName().toLowerCase());
            else
                sb.append(" ").append(args[i].toLowerCase());

            for (String usage : registeredCommandTable.keySet()) {
                if (usage.equals(sb.toString())) {
                    RegisteredCommand wrapper = registeredCommandTable.get(usage);
                    BedBreakEffectCommand annotation = wrapper.annotation;

                    String[] actualParams = Arrays.copyOfRange(args, annotation.command().split(" ").length - 1, args.length);

                    if (!annotation.permission().equals("") && !sender.hasPermission(annotation.permission())) {
                        sender.sendMessage( Utils.getColor( Messages.NO_PERMISSION));
                        return true;
                    }

                    try {
                        wrapper.method.invoke(wrapper.instance, sender, actualParams);
                        return true;
                    } catch (IllegalAccessException | InvocationTargetException e) {

                        e.printStackTrace();
                    }
                    return true;
                }
            }
        }


        return true;
    }

    final static class RegisteredCommand {
        private final Object instance;

        private final Method method;
        private final BedBreakEffectCommand annotation;

        RegisteredCommand(Method method, Object instance, BedBreakEffectCommand annotation) {
            this.method = method;
            this.instance = instance;
            this.annotation = annotation;
        }
    }

}