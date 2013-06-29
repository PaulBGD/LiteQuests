package main.java.me.ultimate.LiteQuests.QuestManager;

import java.io.File;
import java.util.HashMap;

import main.java.me.ultimate.LiteQuests.LiteQuests;
import main.java.me.ultimate.LiteQuests.Enums.QuestType;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class Manager {

   File questsFile;
   static FileConfiguration questsConfig;
   HashMap<Quest, String> quests = new HashMap<Quest, String>();

   public Manager(final LiteQuests LQ) {
      questsFile = new File(LiteQuests.dataFolder + File.separator + "Quests.yml");
      questsConfig = YamlConfiguration.loadConfiguration(questsFile);
      if (questsConfig.isSet("Quests")) {
         for (String s : questsConfig.getConfigurationSection("Quests").getKeys(false)) {
            final String name = s;
            s = "Quests." + s;
            boolean contType = false;
            QuestType type = null;
            for (final QuestType v : QuestType.values()) {
               if (!contType) {
                  if (questsConfig.getString(s + ".Type").equals(v.name())) {
                     contType = true;
                     type = v;
                  }
               }
            }

            ItemStack item = null;
            Location loc = null;
            EntityType entity = null;
            int amount = 0;

            if (type.equals(QuestType.Delivery)) {
               questsConfig.getItemStack(s + ".DeliveryItem");
            } else if (type.equals(QuestType.Location)) {
               World world = Bukkit.getWorld(questsConfig.getString(s + ".world"));
               int x = questsConfig.getInt(s + ".x");
               int y = questsConfig.getInt(s + ".y");
               int z = questsConfig.getInt(s + ".z");
               loc = new Location(world, x, y, z);
            } else if(type.equals(QuestType.MobKill)){
               String e = questsConfig.getString(s + ".Entity");
               
               for(EntityType t : EntityType.values()){
                  if(e.equalsIgnoreCase(t.name()))
                     entity = t;
               }
               amount = questsConfig.getInt(s + ".Amount");
            }
            if (contType) {
               final Quest quest = new Quest(name, type, item, loc, entity, amount);
               registerQuest(quest);
            }
         }
      }
   }

   public void registerQuest(final Quest quest) {
      if (quests.containsKey(quest))
         quests.remove(quest);
      quests.put(quest, quest.getName());
   }

   public static FileConfiguration getConfig() {
      return questsConfig;
   }

}
