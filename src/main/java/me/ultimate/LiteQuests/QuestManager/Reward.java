package main.java.me.ultimate.LiteQuests.QuestManager;

import main.java.me.ultimate.LiteQuests.EconomyHandler;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Reward {

    RewardType rt;
    ItemStack item;
    double money;
    String command;
    Location tp;

    public Reward(RewardType rt, ItemStack item, double money, String command, Location tp) {
        this.rt = rt;
        this.item = item;
        this.money = money;
        this.command = command;
        this.tp = tp;
    }

    public enum RewardType {
        Money, Item, Command, Teleport
    }

    @SuppressWarnings("deprecation")
    public void giveToPlayer(Player p, Quest quest) {
        if (rt.equals(RewardType.Money)) {
            EconomyHandler.econ.depositPlayer(p.getName(), money);
            return;
        }
        if (rt.equals(RewardType.Item)) {
            p.getInventory().addItem(item);
            p.updateInventory();
            return;
        }
        if (rt.equals(RewardType.Command)) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                    command.replaceAll("%player%", p.getName()).replaceAll("%quest%", quest.getName()));
            return;
        }
        if (rt.equals(RewardType.Teleport)) {
            p.teleport(tp);
            return;
        }
    }
}
