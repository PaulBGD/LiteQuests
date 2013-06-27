package main.java.me.ultimate.LiteQuests.Utils;

import java.util.HashMap;

import main.java.me.ultimate.LiteQuests.Language;
import main.java.me.ultimate.LiteQuests.Enums.QuestType;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.entity.EntityType;

public class QuestCreator implements Listener {

    static HashMap<String, Double> creators = new HashMap<String, Double>();
    static HashMap<String, String> name = new HashMap<String, String>();
    static HashMap<String, QuestType> type = new HashMap<String, QuestType>();
    static HashMap<String, Location> loc = new HashMap<String, Location>();
    static HashMap<String, EntityType> entity = new HashMap<String, EntityType>();

    public static void startCreator(Player p, String qName) {
        if (!creators.containsKey(p.getName())) {
            String questTypes = "| ";
            for (QuestType v : QuestType.values()) {
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
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (creators.containsKey(event.getPlayer().getName())) {
            event.setCancelled(true);
            Player p = event.getPlayer();
            String msg = event.getMessage();
            int words = msg.trim().isEmpty() ? 0 : msg.trim().split("\\s+").length;
            if (creators.get(p.getName()) == 1) {
                if (words == 1) {
                    if (!msg.contains(".")) {
                        boolean invalid = true;
                        for (QuestType t : QuestType.values()) {
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
                QuestType qType = type.get(p.getName());
                if (creators.get(p.getName()) == 2) {
                    if (qType.equals(QuestType.Location)) {
                        Send.sendMessage(p, Language.LOCATION_SET);
                        loc.put(p.getName(), p.getLocation());
                        creators.remove(p.getName());
                        creators.put(p.getName(), (double) 3);
                    } else if (qType.equals(QuestType.MobKill)) {
                        if (words == 1) {
                            boolean valid = false;
                            for (EntityType ent : EntityType.values()) {
                                if (!valid && msg.equalsIgnoreCase(ent.name()) && ent.isAlive()) {
                                    valid = true;
                                    entity.put(p.getName(), ent);
                                    creators.remove(p.getName());
                                    creators.put(p.getName(), (double) 2.5);
                                    Send.sendMessage(p, Language.MOBKILL_SET.replaceAll("%entity%", ent.name()));
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
                    //For mob killing
                } else if (creators.get(p.getName()) == 3) {
                    //RewardType
                } else if (creators.get(p.getName()) == 4) {
                    //Reward Amount/Item
                } else if (creators.get(p.getName()) == 5) {
                    //NPC
                }
            }
        }
    }

    public void stopCreator(Player p) {

    }

}
