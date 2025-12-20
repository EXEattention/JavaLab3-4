import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import Ability.GooseRally;
import Ability.WindDisorient;
import Characters.live.Goose;
import Characters.unlive.Wind;
import Enums.FormationType;
import Exceptions.FormationException;
import Records.RoundStats;

public class Main {
    private static WindDisorient windAbility = new WindDisorient();
    private static GooseRally gooseAbility = new GooseRally();

    private static FormationType getFormationType(List<Goose> squad) throws FormationException {
        int totalHp = 0;
        for (Goose g : squad)
            totalHp += g.getHp();

        if (totalHp < 50) {
            throw new FormationException("Строй разлетелся (HP < 50)");
        }

        switch (squad.size()) {
            case 5:
                return FormationType.PENTAGON;
            case 4:
                return FormationType.SQUARE;
            case 3:
                return FormationType.TRIANGLE;
            default:
                return FormationType.NONE;
        }
    }

    private static boolean battle(Wind wind, ArrayList<Goose> allGooses) throws InterruptedException {
        int round = 1;

        while (!allGooses.isEmpty() && wind.isAlive()) {

            RoundStats stats = new RoundStats(round, wind.getEnergy(), allGooses.size());
            System.out.println("\n=== РАУНД " + round + " | " + stats + " ===");
            Thread.sleep(800);

            int baseDamage = wind.performAttack();

            for (int i = 0; i < allGooses.size(); i += 5) {
                int endIndex = Math.min(i + 5, allGooses.size());
                List<Goose> squad = allGooses.subList(i, endIndex);

                if (squad.isEmpty())
                    continue;
                windAbility.use(squad);

                Iterator<Goose> it = squad.iterator();
                while (it.hasNext()) {
                    Goose g = it.next();
                    if (!g.isAlive()) {
                        it.remove();
                    }
                }

                if (squad.isEmpty()) {
                    System.out.println(" => Вся группа рассеяна");
                    continue;
                }
                gooseAbility.use(squad);
                double multiplier = 1.0;

                try {
                    FormationType type = getFormationType(squad);

                    switch (type) {
                        case PENTAGON:
                            System.out.println("ПЕНТАГОН - Урон 50%");
                            multiplier = 0.5;
                            break;
                        case SQUARE:
                            System.out.println("КВАДРАТ - Урон 60%");
                            multiplier = 0.6;
                            break;
                        case TRIANGLE:
                            System.out.println("ТРЕУГОЛЬНИК - Урон 70%");
                            multiplier = 0.7;
                            break;
                        case NONE:
                            System.out.println("НЕТ ФИГУРЫ -  Урон 100%");
                            multiplier = 1.0;
                            break;
                    }
                } catch (FormationException e) {
                    System.out.println(e.getMessage());
                    System.out.println(" => Защита не работает.");
                    multiplier = 1.0;
                }

                int realDamage = (int) (baseDamage * multiplier);

                for (Goose g : squad) {
                    if (g.isAlive()) {
                        g.takeDamage(realDamage);
                    }
                }
                System.out.println(" => урон: " + realDamage);

                wind.reduceEnergy(5);
                if (!wind.isAlive())
                    break;
            }

            Iterator<Goose> globalIt = allGooses.iterator();
            while (globalIt.hasNext()) {
                Goose g = globalIt.next();
                if (!g.isAlive()) {
                    globalIt.remove();
                }
            }

            if (allGooses.isEmpty()) {
                System.out.println("Все гуси погибли. Никольс упал");
                return false;
            }
            if (!wind.isAlive()) {
                System.out.println("Ветер стих.");
                return true;
            }
            round++;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        final int QUANTITYGOOSES = (int) (Math.random() * 16 + 5);
        ArrayList<Goose> flock = new ArrayList<>();
        Wind wind = new Wind((int) (Math.random() * 100 + 150));

        for (int i = 0; i < QUANTITYGOOSES; i++) {
            flock.add(new Goose("Гусь №" + (i + 1)));
        }

        System.out.println("======= ЗАПУСК СИМУЛЯЦИИ =======");
        if (QUANTITYGOOSES > 0)
            battle(wind, flock);
    }
}