package Characters.unlive;

import Characters.GameEntity;

public class Wind extends GameEntity {
    private int energy;

    public Wind(int energy) {
        super("Ветер");
        this.energy = energy;
    }

    public int getEnergy() {
        return energy;
    }

    public void reduceEnergy(int amount) {
        this.energy -= amount;
        if (this.energy < 0)
            this.energy = 0;
    }

    @Override
    public String getRole() {
        return "Агрессор";
    }

    @Override
    public boolean isAlive() {
        return energy > 0;
    }

    public int performAttack() {
        boolean isCritical = Math.random() < 0.2;
        int baseDamage = (int) (Math.random() * 20 + 20);

        if (isCritical) {
            System.out.println("Ветер атакует критическим ударом");
            return baseDamage * 2;
        } else {
            System.out.println("Ветер атакует");
            return baseDamage;
        }
    }
}