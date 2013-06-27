package main.java.me.ultimate.LiteQuests.Utils;

import java.util.HashMap;

import main.java.me.ultimate.LiteQuests.Language;
import main.java.me.ultimate.LiteQuests.Enums.QuestType;
import main.java.me.ultimate.LiteQuests.QuestManager.Reward;
import main.java.me.ultimate.LiteQuests.QuestManager.Reward.RewardType;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class QuestCreator implements Listener {

   static HashMap<String, Double> creators = new HashMap<String, Double>();
   static HashMap<String, String> name = new HashMap<String, String>();
   static HashMap<String, QuestType> type = new HashMap<String, QuestType>();
   static HashMap<String, Location> loc = new HashMap<String, Location>();
   static HashMap<String, EntityType> entity = new HashMap<String, EntityType>();
   static HashMap<String, Integer> killAmount = new HashMap<String, Integer>();
   static HashMap<String, RewardType> rewardType = new HashMap<String, RewardType>();
   static HashMap<String, Reward> reward = new HashMap<String, Reward>();

   public static void startCreator(final Player p, final String qName) {
      if (!creators.containsKey(p.getName())) {
         String questTypes = "| ";
         for (final QuestType v : QuestType.values()) {
            if (v != QuestType.None)
               questTypes = questTypes + v.name() + " | ";
         }
         Send.sendMessage(p, Language.STARTED_QUEST_CREATOR.replaceAll("%questtypes%", questTypes));
         creators.put(p.getName(), (double) 1);
         name.put(p.getName(), qName);
      } else {
         Send.sendMessage(p, Language.ALREADY_CREATING_QUEST);
      }
   }

   @EventHandler(priority = EventPriority.HIGHEST)
   public void onPlayerChat(final AsyncPlayerChatEvent event) {
      if (creators.containsKey(event.getPlayer().getName())) {
         event.setCancelled(true);
         final Player p = event.getPlayer();
         String msg = event.getMessage();
         final int words = msg.trim().isEmpty() ? 0 : msg.trim().split("\\s+").length;
         if (creators.get(p.getName()) == 1) {
            if (words == 1) {
               if (!msg.contains(".")) {
                  boolean invalid = true;
                  for (final QuestType t : QuestType.values()) {
                     if (msg.equals(t.name())) {
                        type.put(p.getName(), t);
                        creators.remove(p.getName());
                        creators.put(p.getName(), (double) 2);
                        invalid = false;
                        String n = null;
                        if (t.equals(QuestType.Location))
                           n = Language.LOCATION_SETUP;
                        if (t.equals(QuestType.MobKill))
                           n = Language.MOBKILL_SETUP;
                        Send.sendMessage(p,
                              Language.SET_QUEST_TYPE.replaceAll("%type%", t.name()).replaceAll("%next%", n));
                        return;
                     }
                  }
                  if (invalid) {
                     Send.sendMessage(p, Language.UNVALID_TYPE);
                  }
               } else {
                  Send.sendMessage(p, Language.NO_PERIODS);
               }
            } else {
               Send.sendMessage(p, Language.TOO_LONG);
            }
         }
         if (creators.get(p.getName()) > 1) {
            final QuestType qType = type.get(p.getName());
            if (creators.get(p.getName()) == 2) {
               String rewardTypes = "| ";
               for (final RewardType v : RewardType.values()) {
                  if (v != RewardType.None)
                     rewardTypes = rewardTypes + v.name() + " | ";
               }
               if (qType.equals(QuestType.Location)) {
                  Send.sendMessage(p, Language.LOCATION_SET.replaceAll("%types%", rewardTypes));
                  loc.put(p.getName(), p.getLocation());
                  creators.remove(p.getName());
                  creators.put(p.getName(), (double) 3);
               } else if (qType.equals(QuestType.MobKill)) {
                  if (words == 1) {
                     boolean valid = false;
                     for (final EntityType ent : EntityType.values()) {
                        if (!valid && msg.equalsIgnoreCase(ent.name()) && ent.isAlive()) {
                           valid = true;
                           entity.put(p.getName(), ent);
                           creators.remove(p.getName());
                           creators.put(p.getName(), 2.5);
                           Send.sendMessage(p, Language.MOBKILL_SET.replaceAll("%entity%", ent.name().toLowerCase()));
                        }
                     }
                     if (!valid) {
                        Send.sendMessage(p, Language.UNVALID_TYPE);
                     }
                  } else {
                     Send.sendMessage(p, Language.TOO_LONG);
                  }
               }
            } else if (creators.get(p.getName()) == 2.5) {
               if (words == 1) {
                  if (!msg.contains(".") && IsInteger.check(msg)) {
                     String rewardTypes = "| ";
                     for (final RewardType v : RewardType.values()) {
                        if (v != RewardType.None)
                           rewardTypes = rewardTypes + v.name() + " | ";
                     }
                     Send.sendMessage(p,
                           Language.SET_MOBKILL_AMOUNT.replaceAll("%int%", msg).replaceAll("%types%", rewardTypes));
                     killAmount.put(p.getName(), Integer.parseInt(msg));
                     creators.remove(p.getName());
                     creators.put(p.getName(), (double) 3);
                  } else {
                     Send.sendMessage(p, Language.INVALID_NUMBER);
                  }
               } else {
                  Send.sendMessage(p, Language.TOO_LONG);
               }
            } else if (creators.get(p.getName()) == 3) {
               if (words == 1) {
                  boolean cont = false;
                  for (final RewardType v : RewardType.values()) {
                     if (!cont && !v.equals(RewardType.None)) {
                        if (msg.equalsIgnoreCase(v.name())) {
                           cont = true;
                           creators.remove(p.getName());
                           creators.put(p.getName(), (double) 4);
                           rewardType.put(p.getName(), v);
                           String next = "ERROR";
                           if (v != null)
                              if (v.equals(RewardType.Command))
                                 next = Language.UNVALID_TYPE;
                              else if (v.equals(RewardType.Item))
                                 next = Language.PUT_ITEM_IN_HAND;
                              else if (v.equals(RewardType.Teleport))
                                 next = Language.LOCATION_SETUP;
                           Send.sendMessage(p,
                                 Language.REWARD_TYPE_SET.replaceAll("%type%", v.name()).replaceAll("%next%", next));
                        }
                     }
                  }
                  if (!cont) {
                     Send.sendMessage(p, Language.UNVALID_TYPE);
                     return;
                  }
               } else {
                  Send.sendMessage(p, Language.TOO_LONG);
                  return;
               }
            } else if (creators.get(p.getName()) == 4) {
               final RewardType rt = rewardType.get(p.getName());
               Reward rew = null;
               if (rt.equals(RewardType.Command)) {
                  if (msg.startsWith("/"))
                     msg = msg.replaceFirst("/", "");
                  rew = new Reward(rt, null, 0, msg, null);
               } else if (rt.equals(RewardType.Item)) {

               } else if (rt.equals(RewardType.Money)) {
                  if (words == 1 && IsInteger.check(msg) && msg.contains(".")) {

                  } else {
                     Send.sendMessage(p, Language.INVALID_NUMBER);
                     return;
                  }
               } else if (rt.equals(RewardType.Teleport)) {

               } else {
                  Send.sendMessage(p, Language.UNVALID_TYPE);
                  return;
               }
               if (rew == null) {
                  Send.sendMessage(p, Language.UNVALID_TYPE);
                  return;
               }
               reward.put(p.getName(), rew);
            } else if (creators.get(p.getName()) == 5) {
               //NPC
            }
         }
      }
   }

   public void stopCreator(final Player p) {

   }

}
