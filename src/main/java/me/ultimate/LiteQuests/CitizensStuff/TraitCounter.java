package main.java.me.ultimate.LiteQuests.CitizensStuff;

import java.util.Date;
import java.util.HashMap;

import net.citizensnpcs.api.npc.NPC;

import org.bukkit.entity.Player;

public class TraitCounter {

   static HashMap<String, Long> timer = new HashMap<String, Long>();

   public static void traitCounter(final NPC npc, final Player p) {
      if (!timer.containsKey(p.getName())) {
         final Long eventoccured = new Date().getTime();
         timer.put(p.getName(), eventoccured);
      } else {
         final Long lapse = new Date().getTime() - timer.get(p.getName());
         if (lapse >= 200) {
            final Long eventoccured = new Date().getTime();
            timer.put(p.getName(), eventoccured);
            //Start the quest!
         }
      }
   }
}
