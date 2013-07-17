package main.java.me.ultimate.LiteQuests;

import java.io.File;

import main.java.me.ultimate.LiteQuests.CitizensStuff.QuestsTrait;
import main.java.me.ultimate.LiteQuests.Command.Quests.MainCommand;
import main.java.me.ultimate.LiteQuests.Enums.LogType;
import main.java.me.ultimate.LiteQuests.QuestManager.Manager;
import main.java.me.ultimate.LiteQuests.QuestManager.QuestListener;
import main.java.me.ultimate.LiteQuests.Utils.Logger;
import main.java.me.ultimate.LiteQuests.Utils.QuestCreator;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.trait.TraitInfo;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LiteQuests extends JavaPlugin {

   public static File dataFolder = null;
   private File languageFile;
   private FileConfiguration languageConfig;
   private Language Language;

   @Override
   public void onEnable() {
   
   
      dataFolder = getDataFolder();
      EconomyHandler.setupEconomy();
      if (getServer().getPluginManager().getPlugin("Citizens") == null) {
         new Logger("Could not find Citizens! Disabling..", LogType.Horrible);
         getServer().getPluginManager().disablePlugin(this);
         return;
      }
      new Manager(this);
      CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(QuestsTrait.class).withName("LiteQuests"));
      languageFile = new File(dataFolder + File.separator + "Messages.yml");
      languageConfig = YamlConfiguration.loadConfiguration(languageFile);
      Language = new Language(languageConfig);
      //if (!languageFile.exists()) {
      Language.setupMessageDefaults(languageFile, languageConfig);
      languageConfig = YamlConfiguration.loadConfiguration(languageFile);
      Language = new Language(languageConfig);
      //}
      getCommand("quests").setExecutor(new MainCommand());
      final PluginManager pm = getServer().getPluginManager();
      pm.registerEvents(new QuestListener(), this);
      pm.registerEvents(new QuestCreator(), this);
   }

   public Language getLanguage() {
      return Language;
   }

}
