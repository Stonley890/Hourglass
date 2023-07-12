package io.github.stonley890.hourglass.player;

import java.util.ArrayList;
import java.util.List;

public class PlayerMemory {

    public List<Integer> playerAbilities = new ArrayList<>();

    public PlayerMemory() {
        for (int i = 0; i < 22; i++) {
            playerAbilities.add(0);
        }
    }

    public void setAbility(int index, int value) {
        playerAbilities.set(index, value);
    }

    public int getAbility(int index) {
        return playerAbilities.get(index);
    }
}
