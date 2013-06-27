package main.java.me.ultimate.LiteQuests.QuestManager;

import java.io.File;
import java.util.HashMap;

import main.java.me.ultimate.LiteQuests.LiteQuests;
import main.java.me.ultimate.LiteQuests.Enums.QuestType;
import main.java.me.ultimate.LiteQuests.QuestManager.Reward.RewardType;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
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
            boolean contReward = false;
            QuestType type = null;
            for (final QuestType v : QuestType.values()) {
               if (!contType) {
                  if (questsConfig.getString(s + ".Type").equals(v.name())) {
                     contType = true;
                     type = v;
                  }
               }
            }
            RewardType reward = null;
            for (final RewardType v : RewardType.values()) {
               if (!contReward) {
                  if (questsConfig.getString(s + ".RewardType").equals(v.name())) {
                     contReward = true;
                     reward = v;
                  }
               }
            }
            ItemStack item = null;
            String command = null;
            double money = 0;
            Location tp = null;
            if (reward.equals(RewardType.Item)) {
               final int itemId = questsConfig.getInt(s + ".Reward.ItemId");
               final short itemData = (short) questsConfig.getDouble(s + ".Reward.ItemData");
               final int itemAmount = questsConfig.getInt(s + ".Reward.ItemAmount");
               item = new ItemStack(Material.getMaterial(itemId), itemAmount, itemData);
            } else if (reward.equals(RewardType.Money)) {
               money = questsConfig.getDouble(s + ".Reward.Money");
            } else if (reward.equals(RewardType.Teleport)) {
               final World world = Bukkit.getWorld(questsConfig.getString(s + ".Reward.world"));
               final int x = questsConfig.getInt(s + ".Reward.x");
               final int y = questsConfig.getInt(s + ".Reward.y");
               final int z = questsConfig.getInt(s + ".Reward.z");
               final float pitch = questsConfig.getInt(s + ".Reward.pitch");
               final float yaw = questsConfig.getInt(s + ".Reward.yaw");
               tp = new Location(world, x, y, z, yaw, pitch);
            } else if (reward.equals(RewardType.Command)) {
               command = questsConfig.getString(s + ".Reward.Command");
            }

            if (contType) {
               final Quest quest = new Quest(name, type, new Reward(reward, item, money, command, tp));
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
