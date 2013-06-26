package me.ultimate.LiteQuests;

import java.io.File;

import me.ultimate.LiteQuests.CitizensStuff.QuestsTrait;
import me.ultimate.LiteQuests.Command.Quests.MainCommand;
import me.ultimate.LiteQuests.Enums.LogType;
import me.ultimate.LiteQuests.QuestManager.Manager;
import me.ultimate.LiteQuests.Utils.Logger;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.trait.TraitInfo;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class LiteQuests extends JavaPlugin {

    public static File dataFolder = null;
    private File languageFile;
    private FileConfiguration languageConfig;
    private Language Language;

    public void onEnable() {
        dataFolder = getDataFolder();
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
        getCommand("quests").setExecutor(new MainCommand(this));
    }

    public Language getLanguage() {
        return Language;
    }
}
