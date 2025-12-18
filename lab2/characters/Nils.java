package lab2.characters;

import lab2.base.Entity;
import lab2.interfaces.Defender;
import lab2.types.Position;

public class Nils extends Entity implements Defender {
    private boolean isFalling;

    public Nils() {
        super("Нильс", new Position(10, 10, 100));
        this.isFalling = false;
    }

    @Override
    public void defend(int damage) {
        if (isFalling) {
            fall();
        } else {
            System.out.println("Нильс изо всех сил держится за перья Мартина!");
        }
    }

    public void fall() {
        this.isFalling = true;
        this.position = this.position.changeAltitude(-35.0);

        System.out.println("Нильс падает Высота: " + String.format("%.1f", this.position.altitude()));

        if (this.position.altitude() <= 0) {
            land();
        }
    }

    public void startFalling() {
        this.isFalling = true;
        System.out.println("Нильс не удержался на своем крылатом коне");
        System.out.println("Его кружит то вверх ногами, то вниз головой");
    }

    private void land() {
        System.out.println("Вот вот он ударится об землю");
        System.out.println("НО земля расступилась под ним");
        this.position = new Position(position.x(), position.y(), -10);
    }

    @Override
    public boolean isDefeated() {
        return isFalling;
    }

    @Override
    public void displayState() {
        System.out.println("Нильс Падает: " + isFalling);
    }
}