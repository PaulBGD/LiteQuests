package me.ultimate.LiteQuests;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;

public class Language {

    public Language(FileConfiguration msgConfig) {
        NOT_ENOUGH_ARGUMENTS = msgConfig.getString("NOT_ENOUGH_ARGUMENTS");
        NOT_A_PLAYER = msgConfig.getString("NOT_A_PLAYER");
        ARGUMENT_DOESNT_EXIST = msgConfig.getString("ARGUMENT_DOESNT_EXIST");
        PLAYER_NOT_HAVE_PERMISSION = msgConfig.getString("PLAYER_NOT_HAVE_PERMISSION");
    }

    public String NOT_ENOUGH_ARGUMENTS;
    public String NOT_A_PLAYER;
    public String ARGUMENT_DOESNT_EXIST;
    public String PLAYER_NOT_HAVE_PERMISSION;

    public void setupMessageDefaults(File file, FileConfiguration msg) {
        msg.set("NOT_ENOUGH_ARGUMENTS", "You do not have enough arguments!");
        msg.set("NOT_A_PLAYER", "You are not a player!");
        msg.set("ARGUMENT_DOESNT_EXIST", "&4You did something wrong! That argument doesn't exist!");
        msg.set("PLAYER_NOT_HAVE_PERMISSION", "&4You do not have the permission %perm%!");
        try {
            msg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
