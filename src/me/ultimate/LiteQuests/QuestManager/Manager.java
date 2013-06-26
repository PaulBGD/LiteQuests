package me.ultimate.LiteQuests.QuestManager;

import java.io.File;
import java.util.HashMap;

import me.ultimate.LiteQuests.LiteQuests;
import me.ultimate.LiteQuests.Enums.QuestType;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Manager {

    File questsFile;
    FileConfiguration questsConfig;
    HashMap<Quest, String> quests = new HashMap<Quest, String>();

    public Manager(LiteQuests LQ) {
        questsFile = new File(LiteQuests.dataFolder + File.separator + "Quests.yml");
        questsConfig = YamlConfiguration.loadConfiguration(questsFile);
        if (questsConfig.isSet("Quests")) {
            for (String s : questsConfig.getConfigurationSection("Quests").getKeys(false)) {
                String name = s;
                s = "Quests." + s;
                boolean cont = false;
                QuestType type = null;
                for (QuestType v : QuestType.values()) {
                    if (!cont) {
                        if (questsConfig.getString(s + ".Type").equals(v.name())) {
                            cont = true;
                            type = v;
                        }
                    }
                }
                if (cont) {
                    Quest quest = new Quest(name, type, null);
                    registerQuest(quest);
                }
            }
        }
    }

    public void registerQuest(Quest quest) {
        if (quests.containsKey(quest))
            quests.remove(quest);
        quests.put(quest, quest.getName());
    }
    
    public FileConfiguration getConfig(){
        return questsConfig;
    }

}
