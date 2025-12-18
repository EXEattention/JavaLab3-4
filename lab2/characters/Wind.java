package lab2.characters;

import lab2.base.Entity;
import lab2.interfaces.Attacker;
import lab2.interfaces.Defender;
import lab2.types.Position;
import lab2.exceptions.FormationBrokenException;
import lab2.exceptions.WindExhaustedException;
import java.util.Random;

public class Wind extends Entity implements Attacker {
    private int power;

    public Wind(String name, int power) {
        super(name, new Position(0, 0, 1000));
        this.power = power;
    }

    @Override
    public void attack(Defender target) throws FormationBrokenException {
        if (power <= 0) {
            throw new WindExhaustedException("Ветер выдохся и утих.");
        }

        System.out.println(this.name + " не сдается! Он собирается с силами...");

        Random random = new Random();
        int damage = random.nextInt(20) + 15;

        if (damage > 30) {
            System.out.println(this.name + " В ЯРОСТИ ринулся на гусей");
        } else {
            System.out.println(this.name + " пытается сбить стаю порывами воздуха.");
        }

        power -= 5;
        target.defend(damage);
    }

    @Override
    public void displayState() {
        System.out.println("(Ветер) Сила: " + power);
    }
}