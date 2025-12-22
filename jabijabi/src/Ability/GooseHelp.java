package Ability;

import java.util.List;
import Characters.live.Goose;
import Interfaces.Ability;

public class GooseHelp implements Ability {
    @Override
    public boolean use(List<Goose> squad) {
        if (squad.isEmpty())
            return false;

        if (Math.random() < 0.2) {
            int max = -1;
            Goose Nmax = null;
            int min = 10000;
            Goose Nmin = null;

            for (Goose i : squad) {
                if (max < i.getHp()) {
                    max = i.getHp();
                    Nmax = i;
                }
                if (min > i.getHp()) {
                    min = i.getHp();
                    Nmin = i;
                }
            }

            if (Nmin != null && Nmax != null) {
                Goose reserv = Nmin;

                Nmin.heal((Nmin.getHp() + Nmax.getHp()) / 2);

                Nmin.takeDamage((reserv.getHp() + Nmax.getHp()) / 2);

                System.out.println("Способность уравнивания хп между максимальным в группе и меньшим");
                return true;
            }
        }
        return false;
    }
}