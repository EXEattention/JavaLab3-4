package lab2.base;

import lab2.types.Position;
import java.util.Objects;

public abstract class Entity {
    protected String name;
    protected Position position;

    public Entity(String name, Position position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public abstract void displayState();

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Entity entity = (Entity) o;
        return Objects.equals(name, entity.name) &&
                Objects.equals(position, entity.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position);
    }

    @Override
    public String toString() {
        return "Сущность{" + "имя='" + name + '\'' + ", позиция=" + position + '}';
    }
}