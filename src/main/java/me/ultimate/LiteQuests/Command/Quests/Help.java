package main.java.me.ultimate.LiteQuests.Command.Quests;

import java.util.Map;

import main.java.me.ultimate.LiteQuests.Language;
import main.java.me.ultimate.LiteQuests.Utils.Send;

import org.bukkit.entity.Player;

public class Help implements BaseCommand {

    @Override
    public void perform(Player p, String allArgs, String[] args) {
        if (args.length == 1) {
            Send.sendMessage(p, "&8 -=-=-=-=-=-=- &0[ &aLiteQuests Admin Help &0] &8-=-=-=-=-=-=-", false);
            for (Map.Entry<String, BaseCommand> entry : MainCommand.commandClasses.entrySet()) {
                BaseCommand cmd = entry.getValue();
                if (!cmd.isAlias())
                    Send.sendMessage(p, "&a" + cmd.getUsage() + " &8--> &a" + cmd.getHelpMessage(), false);
            }
        } else {
            BaseCommand cmdClass = null;
            boolean cont = false;
            for (Map.Entry<String, BaseCommand> entry : MainCommand.commandClasses.entrySet()) {
                if (!cont) {
                    if (entry.getKey().equalsIgnoreCase(args[1])) {
                        cont = true;
                        cmdClass = entry.getValue();
                    }
                }
            }
            if (cont) {
                Send.sendMessage(p, "&8 -=-=-=-=-=-=- &0[ &aLiteQuests Admin Help &0] &8-=-=-=-=-=-=-", false);
                Send.sendMessage(p, "&a" + cmdClass.getUsage() + " &8--> &a" + cmdClass.getHelpMessage(),
                        false);
            } else {
                Send.sendMessage(p, Language.ARGUMENT_DOESNT_EXIST);
            }
        }
    }

    @Override
    public String getCommand() {
        return "help";
    }

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public String getHelpMessage() {
        return "Shows you basic help";
    }

    @Override
    public boolean isAlias() {
        return false;
    }

    @Override
    public BaseCommand getAlias() {
        return null;
    }

    @Override
    public String getUsage() {
        return "/quests help [command]";
    }

}
