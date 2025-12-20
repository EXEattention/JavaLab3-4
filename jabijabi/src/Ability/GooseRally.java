package Ability;

import java.util.List;
import Characters.live.Goose;

public class GooseRally implements Ability {

    @Override
    public boolean use(List<Goose> squad) {
        int totalHp = 0;
        for (Goose g : squad)
            totalHp += g.getHp();

        if (totalHp < 300 && Math.random() < 0.4) {
            System.out.println("Гуси применяют способность сплочения(все в группе лечаться на 15hp)");
            for (Goose g : squad) {
                if (g.isAlive()) {
                    int newHp = g.getHp() + 15;
                    if (newHp > 100)
                        newHp = 100;
                    g.heal(15);
                }
            }
            return true;
        }
        return false;
    }
}