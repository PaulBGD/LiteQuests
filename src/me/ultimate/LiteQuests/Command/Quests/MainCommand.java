package me.ultimate.LiteQuests.Command.Quests;

import java.util.HashMap;
import java.util.Map;

import me.ultimate.LiteQuests.Language;
import me.ultimate.LiteQuests.LiteQuests;
import me.ultimate.LiteQuests.Utils.Send;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {

    public static HashMap<String, BaseCommand> commandClasses = new HashMap<String, BaseCommand>();

    private LiteQuests LQ;

    public MainCommand(LiteQuests LQ) {
        this.LQ = LQ;
        registerArgument(new Help());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Language msg = LQ.getLanguage();
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length > 0) {
                boolean cont = false;
                BaseCommand cmdClass = null;
                for (Map.Entry<String, BaseCommand> entry : commandClasses.entrySet()) {
                    if (!cont) {
                        if (entry.getKey().equalsIgnoreCase(args[0])) {
                            cont = true;
                            cmdClass = entry.getValue();
                        }
                    }
                }
                if (cont) {
                    int argsNeeded = cmdClass.getLength();
                    if (args.length - 1 == argsNeeded) {
                        if (p.hasPermission(cmdClass.getPermission())
                                || p.hasPermission("LiteQuests." + cmdClass.getCommand())) {
                            if (args[argsNeeded] == null) {
                                args[argsNeeded] = "Nothing";
                            }
                            StringBuilder sb = new StringBuilder();
                            for (int i = argsNeeded; i < args.length; i++) {
                                sb.append(args[i]).append(" ");
                            }

                            String allArgs = sb.toString().trim();
                            cmdClass.perform(p, allArgs, args);
                            return true;
                        } else {
                            Send.sendMessage(p, msg.PLAYER_NOT_HAVE_PERMISSION);
                            return true;
                        }
                    } else {
                        Send.sendMessage(p, msg.NOT_ENOUGH_ARGUMENTS);
                        return true;
                    }
                } else {
                    Send.sendMessage(p, msg.ARGUMENT_DOESNT_EXIST);
                    return true;
                }
            } else {
                Send.sendMessage(p, msg.NOT_ENOUGH_ARGUMENTS);
                return true;
            }
        } else {
            Send.sendMessage(sender, msg.NOT_A_PLAYER);
            return true;
        }
    }

    void registerArgument(BaseCommand baseCmd) {
        commandClasses.put(baseCmd.getCommand(), baseCmd);
    }
}
