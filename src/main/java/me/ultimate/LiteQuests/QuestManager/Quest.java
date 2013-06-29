package main.java.me.ultimate.LiteQuests.QuestManager;

import main.java.me.ultimate.LiteQuests.Enums.QuestType;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class Quest {

   String name;
   QuestType type;
   
   //Requirements
   ItemStack item;
   Location loc;
   EntityType entity;
   int amount;

   public Quest(final String name, final QuestType type, ItemStack item, Location loc, EntityType entity, int amount) {
      this.name = name;
      this.type = type;
      
      this.item = item;
      this.loc = loc;
      this.entity = entity;
      this.amount = amount;
   }

   public String getName() {
      return name;
   }

   public QuestType getType() {
      return type;
   }
}
