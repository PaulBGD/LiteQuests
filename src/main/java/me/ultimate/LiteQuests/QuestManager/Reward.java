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

   public Object getFromRewardType(final RewardType type) {
      if (type.equals(RewardType.Command))
         return command;
      if (type.equals(RewardType.Item))
         return item;
      if (type.equals(RewardType.Money))
         return money;
      if (type.equals(RewardType.Teleport))
         return tp;
      return null;
   }

   public Reward(final RewardType rt, final ItemStack item, final double money, final String command, final Location tp) {
      this.rt = rt;
      this.item = item;
      this.money = money;
      this.command = command;
      this.tp = tp;
   }

   public enum RewardType {
      Money, Item, Command, Teleport, None
   }

   @SuppressWarnings("deprecation")
   public void giveToPlayer(final Player p, final Quest quest) {
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
