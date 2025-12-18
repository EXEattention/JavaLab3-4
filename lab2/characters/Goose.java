package lab2.characters;

import lab2.base.Entity;
import lab2.interfaces.Defender;
import lab2.types.Position;

public class Goose extends Entity implements Defender {
    private int stamina;

    public Goose(String name) {
        super(name, new Position(10, 10, 100));
        this.stamina = 100;
    }

    @Override
    public void defend(int damage) {
        this.stamina -= damage;
        System.out.println("   -> " + this.name + " борется, выносливость: " + stamina);
    }

    @Override
    public boolean isDefeated() {
        return stamina <= 0;
    }

    @Override
    public void displayState() {
        System.out.println("Гусь " + name + " летит.");
    }
}