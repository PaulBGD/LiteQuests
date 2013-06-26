package me.ultimate.LiteQuests.CitizensStuff;

import me.ultimate.LiteQuests.LiteQuests;
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
    public void onClick(NPCRightClickEvent event) throws Exception {
        TraitCounter.traitCounter(event.getNPC().getName(), event.getClicker());
    }
}
