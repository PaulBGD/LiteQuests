package main.java.me.ultimate.LiteQuests.QuestManager;

import java.util.HashMap;

import main.java.me.ultimate.LiteQuests.Enums.QuestType;

import org.bukkit.entity.Player;

public class PlayerManager {

   static HashMap<String, Quest> players = new HashMap<String, Quest>();

   public static void setPlayerQuest(final Player p, final Quest quest) {
      if (players.containsKey(p.getName())) {
         players.remove(p.getName());
      }
      if (quest != null)
         players.put(p.getName(), quest);
   }

   public static Quest getPlayerQuest(final Player p) {
      if (!players.containsKey(p.getName()))
         return null;
      return players.get(p.getName());
   }

   public static QuestType getQuestType(final Player p) {
      final Quest quest = getPlayerQuest(p);
      if (quest == null)
         return QuestType.None;
      return quest.getType();
   }

   public static boolean playerHasQuest(final Player p) {
      return players.containsKey(p.getName());
   }
}
