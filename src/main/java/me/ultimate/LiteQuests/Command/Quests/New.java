package main.java.me.ultimate.LiteQuests.Command.Quests;

import main.java.me.ultimate.LiteQuests.Language;
import main.java.me.ultimate.LiteQuests.QuestManager.Manager;
import main.java.me.ultimate.LiteQuests.Utils.QuestCreator;
import main.java.me.ultimate.LiteQuests.Utils.Send;

import org.bukkit.entity.Player;

public class New implements BaseCommand {

   @Override
   public void perform(final Player p, final String allArgs, final String[] args) {
      if (!Manager.getConfig().isSet("Quests." + args[1])) {
         if (!args[1].contains(".")) {
            QuestCreator.startCreator(p, args[1]);
         } else {
            Send.sendMessage(p, Language.NO_PERIODS);
         }
      } else {
         Send.sendMessage(p, Language.QUEST_ALREADY_EXISTS.replaceAll("%quest%", args[1]));
      }
   }

   @Override
   public String getCommand() {
      return "new";
   }

   @Override
   public int getLength() {
      return 1;
   }

   @Override
   public String getHelpMessage() {
      return "Creates a new quest";
   }

   @Override
   public boolean isAlias() {
      return false;
   }

   @Override
   public BaseCommand getAlias() {
      return null;
   }

   @Override
   public String getUsage() {
      return "/quests new <name>";
   }

}
