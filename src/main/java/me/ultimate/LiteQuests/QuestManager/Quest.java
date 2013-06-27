package main.java.me.ultimate.LiteQuests.QuestManager;

import main.java.me.ultimate.LiteQuests.Enums.QuestType;

public class Quest {

    String name;
    QuestType type;
    Reward reward;

    public Quest(String name, QuestType type, Reward reward) {
        this.name = name;
        this.type = type;
        this.reward = reward;
    }

    public String getName() {
        return name;
    }

    public QuestType getType() {
        return type;
    }

    public Reward getReward() {
        return reward;
    }
}
