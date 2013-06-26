package me.ultimate.LiteQuests.Command.Quests;

import org.bukkit.entity.Player;

public abstract interface BaseCommand {

    public abstract void perform(Player p, String allArgs, String[] args);
    public abstract String getCommand();
    public abstract int getLength();
    public abstract String getPermission();
    public abstract String getHelpMessage();

}
