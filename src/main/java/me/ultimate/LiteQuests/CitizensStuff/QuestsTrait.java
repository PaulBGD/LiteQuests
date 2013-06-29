package main.java.me.ultimate.LiteQuests.CitizensStuff;

import main.java.me.ultimate.LiteQuests.LiteQuests;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.trait.Trait;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;

public class QuestsTrait extends Trait {

   LiteQuests plugin;

   public QuestsTrait() {
      super("LiteQuests");
      plugin = (LiteQuests) Bukkit.getServer().getPluginManager().getPlugin("LiteQuests");
   }
   
   @EventHandler
   public void onClick(final NPCRightClickEvent event) throws Exception {
      TraitCounter.traitCounter(event.getNPC(), event.getClicker());
   }
}
