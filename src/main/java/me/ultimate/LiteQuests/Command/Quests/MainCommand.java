package main.java.me.ultimate.LiteQuests.Command.Quests;

import java.util.HashMap;
import java.util.Map;

import main.java.me.ultimate.LiteQuests.Language;
import main.java.me.ultimate.LiteQuests.Utils.Send;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {

   public static HashMap<String, BaseCommand> commandClasses = new HashMap<String, BaseCommand>();

   public MainCommand() {
      registerArgument(new Create());
      registerArgument(new New());
      registerArgument(new Help());
      registerArgument(new QuestionMark());
   }

   @Override
   public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
      if (sender instanceof Player) {
         final Player p = (Player) sender;
         if (args.length > 0) {
            boolean cont = false;
            BaseCommand cmdClass = null;
            for (final Map.Entry<String, BaseCommand> entry : commandClasses.entrySet()) {
               if (!cont) {
                  if (entry.getKey().equalsIgnoreCase(args[0])) {
                     cont = true;
                     cmdClass = entry.getValue();
                  }
               }
            }
            if (cont) {
               final int argsNeeded = cmdClass.getLength();
               if (args.length - 1 >= argsNeeded) {
                  if (p.hasPermission("LiteQuests.Quests." + cmdClass.getCommand())) {
                     if (args[argsNeeded] == null) {
                        args[argsNeeded] = "Nothing";
                     }
                     final StringBuilder sb = new StringBuilder();
                     for (int i = argsNeeded; i < args.length; i++) {
                        sb.append(args[i]).append(" ");
                     }

                     final String allArgs = sb.toString().trim();
                     if (cmdClass.isAlias())
                        cmdClass.getAlias().perform(p, allArgs, args);
                     else
                        cmdClass.perform(p, allArgs, args);
                     return true;
                  } else {
                     Send.sendMessage(p, Language.PLAYER_NOT_HAVE_PERMISSION);
                     return true;
                  }
               } else {
                  Send.sendMessage(p, Language.NOT_ENOUGH_ARGUMENTS + " Try: " + cmdClass.getUsage());
                  return true;
               }
            } else {
               Send.sendMessage(p, Language.ARGUMENT_DOESNT_EXIST);
               return true;
            }
         } else {
            Send.sendMessage(p, Language.NOT_ENOUGH_ARGUMENTS);
            return true;
         }
      } else {
         Send.sendMessage(sender, Language.NOT_A_PLAYER);
         return true;
      }
   }

   void registerArgument(final BaseCommand baseCmd) {
      commandClasses.put(baseCmd.getCommand(), baseCmd);
   }
}
