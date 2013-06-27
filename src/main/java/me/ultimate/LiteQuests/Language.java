package main.java.me.ultimate.LiteQuests;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;

public class Language {

   public Language(final FileConfiguration msgConfig) {
      NOT_ENOUGH_ARGUMENTS = msgConfig.getString("NOT_ENOUGH_ARGUMENTS");
      NOT_A_PLAYER = msgConfig.getString("NOT_A_PLAYER");
      ARGUMENT_DOESNT_EXIST = msgConfig.getString("ARGUMENT_DOESNT_EXIST");
      PLAYER_NOT_HAVE_PERMISSION = msgConfig.getString("PLAYER_NOT_HAVE_PERMISSION");
      QUEST_ALREADY_EXISTS = msgConfig.getString("QUEST_ALREADY_EXISTS");
      NO_PERIODS = msgConfig.getString("NO_PERIODS");
      ALREADY_CREATING_QUEST = msgConfig.getString("ALREADY_CREATING_QUEST");
      STARTED_QUEST_CREATOR = msgConfig.getString("STARTED_QUEST_CREATOR");
      TOO_LONG = msgConfig.getString("TOO_LONG");
      UNVALID_TYPE = msgConfig.getString("UNVALID_TYPE");
      SET_QUEST_TYPE = msgConfig.getString("SET_QUEST_TYPE");
      LOCATION_SETUP = msgConfig.getString("LOCATION_SETUP");
      LOCATION_SET = msgConfig.getString("LOCATION_SET");
      MOBKILL_SETUP = msgConfig.getString("MOBKILL_SETUP");
      MOBKILL_SET = msgConfig.getString("MOBKILL_SET");
      INVALID_NUMBER = msgConfig.getString("INVALID_NUMBER");
      SET_MOBKILL_AMOUNT = msgConfig.getString("SET_MOBKILL_AMOUNT");
      REWARD_TYPE_SET = msgConfig.getString("REWARD_TYPE_SET");
      TYPE_IN_COMMAND = msgConfig.getString("TYPE_IN_COMMAND");
      PUT_ITEM_IN_HAND = msgConfig.getString("PUT_ITEM_IN_HAND");
   }

   public static String NOT_ENOUGH_ARGUMENTS;
   public static String NOT_A_PLAYER;
   public static String ARGUMENT_DOESNT_EXIST;
   public static String PLAYER_NOT_HAVE_PERMISSION;
   public static String QUEST_ALREADY_EXISTS;
   public static String NO_PERIODS;
   public static String ALREADY_CREATING_QUEST;
   public static String STARTED_QUEST_CREATOR;
   public static String TOO_LONG;
   public static String UNVALID_TYPE;
   public static String SET_QUEST_TYPE;
   public static String LOCATION_SETUP;
   public static String MOBKILL_SETUP;
   public static String LOCATION_SET;
   public static String MOBKILL_SET;
   public static String INVALID_NUMBER;
   public static String SET_MOBKILL_AMOUNT;
   public static String REWARD_TYPE_SET;
   public static String TYPE_IN_COMMAND;
   public static String PUT_ITEM_IN_HAND;

   public void setupMessageDefaults(final File file, final FileConfiguration msg) {
      msg.set("PUT_ITEM_IN_HAND", "Now put the item in your hand you wish to set and type anything.   ");
      msg.set("TYPE_IN_COMMAND", "Now type in the command you wish to set.");
      msg.set("REWARD_TYPE_SET", "You have set the reward type to %type%! %next%");
      msg.set("SET_MOBKILL_AMOUNT",
            "You have sent the amount of mobs to kill to: %int%. Now type in the Reward Type. Types: %types%");
      msg.set("INVALID_NUMBER", "Invalid number! Try again.");
      msg.set("MOBKILL_SET", "You have set the entity type to %entity%. Now type in the amount to kill.");
      msg.set("MOBKILL_SETUP", "Now type in what kind of mob you want the player to kill.");
      msg.set("LOCATION_SET",
            "You have set the location to your current spot! Now type in the Reward Type. Types: %types%");
      msg.set("LOCATION_SETUP", "Now go to the spot where you want the location to be, and type in anything.");
      msg.set("SET_QUEST_TYPE", "You have set the quest type to: %type%! %next%");
      msg.set("UNVALID_TYPE", "That is a invalid type! Try again!");
      msg.set("TOO_LONG", "That is too long!");
      msg.set(
            "STARTED_QUEST_CREATOR",
            "You have started the quest creator! Do /quests stop to end it. Now that you have chosen the name, type in chat the type of quest. Valid quest types are %questtypes%");
      msg.set("NO_PERIODS", "You cannot use periods here!");
      msg.set("NOT_ENOUGH_ARGUMENTS", "You do not have enough arguments!");
      msg.set("NOT_A_PLAYER", "You are not a player!");
      msg.set("ARGUMENT_DOESNT_EXIST", "&4You did something wrong! That argument doesn't exist!");
      msg.set("PLAYER_NOT_HAVE_PERMISSION", "&4You do not have the permission %perm%!");
      msg.set("QUEST_ALREADY_EXISTS", "The quest %quest% already exists!");
      msg.set("ALREADY_CREATING_QUEST", "You are already creating a quest! Do /quest stop to stop.");
      try {
         msg.save(file);
      } catch (final IOException e) {
         e.printStackTrace();
      }
   }
}
