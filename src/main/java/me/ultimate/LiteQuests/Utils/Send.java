package main.java.me.ultimate.LiteQuests.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Send {

   public static String prefix = "&8[&aLiteQuests&8]&a ";

   public static void sendMessage(final CommandSender p, final String msg) {
      sendMessage(p, msg, true);
   }

   public static void sendMessage(final CommandSender p, final String msg, final boolean pref) {
      String tPrefix = "";
      if (pref) {
         tPrefix = prefix;
      }
      if (p instanceof Player) {
         p.sendMessage(ChatColor.translateAlternateColorCodes('&', tPrefix + msg));
      } else {
         Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', tPrefix + msg));
      }
   }

}
