package Characters.live;

import Characters.GameEntity;

public class Goose extends GameEntity {
    private int hp = 100;

    public Goose(String name) {
        super(name);
    }

    public int getHp() {
        return hp;
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0)
            this.hp = 0;
    }

    public void heal(int amount) {
        this.hp += amount;
        if (this.hp > 100)
            this.hp = 100; 
    }

    @Override
    public String getRole() {
        return "Защитник";
    }

    public boolean isAlive() {
        return hp > 0;
    }

    @Override
    public String toString() {
        return this.name + " (HP:" + this.hp + ")";
    }
}