package me.ultimate.LiteQuests.Command.Quests;

import java.util.Map;

import me.ultimate.LiteQuests.Utils.Send;

import org.bukkit.entity.Player;

public class Help implements BaseCommand{

    @Override
    public void perform(Player p, String allArgs, String[] args) {
        Send.sendMessage(p, "&8 -=-=-=-=-=-=- &0[ &aLiteQuests Admin Help &0] &8-=-=-=-=-=-=-", false);
        for (Map.Entry<String, BaseCommand> entry : MainCommand.commandClasses.entrySet()) {
            BaseCommand cmd = entry.getValue();
            Send.sendMessage(p, "&a/quests " + cmd.getCommand() + " &8--> &a" + cmd.getHelpMessage(), false);
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
    public String getPermission() {
        return "litequests.quests.help";
    }

    @Override
    public String getHelpMessage() {
        return "Shows you basic help";
    }

}
