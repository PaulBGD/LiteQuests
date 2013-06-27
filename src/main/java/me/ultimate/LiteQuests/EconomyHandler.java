package main.java.me.ultimate.LiteQuests;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class EconomyHandler {

   public static Economy econ = null;

   public static boolean setupEconomy() {
      if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
         return false;
      }
      final RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager()
            .getRegistration(Economy.class);
      if (rsp == null) {
         return false;
      }
      econ = rsp.getProvider();
      return econ != null;
   }
}
