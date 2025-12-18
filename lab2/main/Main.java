package lab2.main;

import lab2.characters.*;
import lab2.types.FormationType;
import lab2.exceptions.FormationBrokenException;
import lab2.exceptions.WindExhaustedException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ВАРИАНТ 26692: СЛУЧАЙНАЯ БИТВА ===");

        Wind wind = new Wind("Северный Ветер", 100);
        Flock flock = new Flock();
        Nils nils = new Nils();

        Random random = new Random();
        int gooseCount = random.nextInt(6) + 1;

        flock.addGoose(new Goose("Мартин (Вожак)"));
        for (int i = 1; i < gooseCount; i++) {
            flock.addGoose(new Goose("Дикий гусь №" + i));
        }

        flock.organize();

        try {
            while (true) {
                System.out.println("\n--- Раунд ---");

                try {
                    wind.attack(flock);
                    flock.checkFormation();
                } catch (FormationBrokenException e) {
                    System.out.println("КРИТИЧЕСКИЙ МОМЕНТ: " + e.getMessage());

                    nils.startFalling();
                    while (nils.isDefeated()) {
                        nils.fall();
                        if (nils.getPosition().altitude() <= 0)
                            break;
                        Thread.sleep(400);
                    }
                    break;
                }

                if (flock.getFormation() != FormationType.SCATTERED) {
                    nils.defend(5);
                } else {
                    System.out.println("Нильс в панике! Строй развалился!");
                }

                Thread.sleep(1000);
            }
        } catch (WindExhaustedException e) {
            System.out.println("ПОБЕДА: " + e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== Сценарий завершен ===");
    }
}