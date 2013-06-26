package me.ultimate.LiteQuests.CitizensStuff;

import java.util.Date;
import java.util.HashMap;

import org.bukkit.entity.Player;

public class TraitCounter {

    static HashMap<String, Long> timer = new HashMap<String, Long>();

    public static void traitCounter(String name, final Player p) {
        if (!timer.containsKey(p.getName())) {
            Long eventoccured = new Date().getTime();
            timer.put(p.getName(), eventoccured);
        } else {
            Long lapse = new Date().getTime() - timer.get(p.getName());
            if (lapse >= 200) {
                Long eventoccured = new Date().getTime();
                timer.put(p.getName(), eventoccured);
                //Start the quest!
            }
        }
    }
}
