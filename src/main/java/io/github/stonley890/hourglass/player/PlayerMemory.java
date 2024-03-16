package io.github.stonley890.hourglass.player;

import io.github.stonley890.hourglass.Ability;
import io.github.stonley890.hourglass.commands.Abilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerMemory {

    public List<Integer> playerAbilities = new ArrayList<>();

    public PlayerMemory() {
        for (int i = 0; i < Abilities.abilities.size(); i++) {
            playerAbilities.add(0);
        }
    }

    public void setAbility(int index, int value) {
        playerAbilities.set(index, value);
    }

    public int getAbility(int index) {
        return playerAbilities.get(index);
    }

    public int getAbility(Ability ability) {
        List<Ability> abilities = Abilities.abilities;
        for (int i = 0; i < abilities.size(); i++) {
            Ability ability1 = abilities.get(i);
            if (Objects.equals(ability1.getName(), ability.getName())) return playerAbilities.get(i);
        }
        return 0;
    }
}
