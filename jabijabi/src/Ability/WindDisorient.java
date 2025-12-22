package Ability;

import java.util.List;
import Characters.live.Goose;
import Interfaces.Ability;

public class WindDisorient implements Ability {

    @Override
    public boolean use(List<Goose> squad) {
        if (squad.isEmpty())
            return false;

        if (Math.random() < 0.3) {
            int randomIndex = (int) (Math.random() * squad.size());
            Goose victim = squad.get(randomIndex);
            System.out.println("Ветер применяет дизориентацюи");
            System.out.println(victim.getName() + " потерял ориентацию и выбыл из строя");
            victim.takeDamage(2280);
            return true;
        }
        return false;
    }
}