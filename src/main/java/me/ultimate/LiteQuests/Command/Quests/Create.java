package main.java.me.ultimate.LiteQuests.Command.Quests;

import org.bukkit.entity.Player;

public class Create implements BaseCommand {

   @Override
   public void perform(final Player p, final String allArgs, final String[] args) {
      //Alias, so do nothing
   }

   @Override
   public String getCommand() {
      return "create";
   }

   @Override
   public int getLength() {
      return new New().getLength();
   }

   @Override
   public String getHelpMessage() {
      return new New().getHelpMessage();
   }

   @Override
   public boolean isAlias() {
      return true;
   }

   @Override
   public BaseCommand getAlias() {
      return new New();
   }

   @Override
   public String getUsage() {
      return new New().getUsage();
   }

}
