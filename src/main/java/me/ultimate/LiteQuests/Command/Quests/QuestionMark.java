package main.java.me.ultimate.LiteQuests.Command.Quests;

import org.bukkit.entity.Player;

public class QuestionMark implements BaseCommand {

   @Override
   public void perform(final Player p, final String allArgs, final String[] args) {
      //Alias, do nothing
   }

   @Override
   public String getCommand() {
      return "?";
   }

   @Override
   public int getLength() {
      return new Help().getLength();
   }

   @Override
   public String getHelpMessage() {
      return new Help().getHelpMessage();
   }

   @Override
   public boolean isAlias() {
      return true;
   }

   @Override
   public BaseCommand getAlias() {
      return new Help();
   }

   @Override
   public String getUsage() {
      return new Help().getUsage();
   }

}
