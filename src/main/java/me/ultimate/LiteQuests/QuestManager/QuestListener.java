package main.java.me.ultimate.LiteQuests.QuestManager;

import main.java.me.ultimate.LiteQuests.Enums.QuestType;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class QuestListener implements Listener {

    @EventHandler
    public void onCreatureDeath(EntityDeathEvent event) {
            if (event.getEntity().getKiller() instanceof Player && event.getEntity().getKiller() != null) {
                if (PlayerManager.playerHasQuest(event.getEntity().getKiller())
                        && PlayerManager.getPlayerQuest(event.getEntity().getKiller()).equals(QuestType.MobKill)) {
                    MobkillQuest.perform(event.getEntity().getKiller(), event.getEntity());
                }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.getFrom().distance(event.getTo()) >= 0.5) {
            if (PlayerManager.playerHasQuest(event.getPlayer())
                    && PlayerManager.getPlayerQuest(event.getPlayer()).equals(QuestType.Location)) {
                LocationQuest.perform(event.getPlayer());
            }
        }
    }
}
