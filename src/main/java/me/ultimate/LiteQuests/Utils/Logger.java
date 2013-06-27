package main.java.me.ultimate.LiteQuests.Utils;

import main.java.me.ultimate.LiteQuests.Enums.LogType;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Logger {

   public Logger(final String log, final LogType type) {
      if (type.equals(LogType.Normal)) {
         Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', Send.prefix + log));
      } else if (type.equals(LogType.Bad)) {
         Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', Send.prefix + "&e" + log));
      } else {
         Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', Send.prefix + "&4" + log));
      }
   }
}
