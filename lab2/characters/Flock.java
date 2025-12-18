package lab2.characters;

import lab2.interfaces.Defender;
import lab2.types.FormationType;
import lab2.exceptions.FormationBrokenException;
import java.util.ArrayList;
import java.util.List;

public class Flock implements Defender {
    private List<Goose> geese;
    private FormationType formation;
    private int integrity;

    public Flock() {
        this.geese = new ArrayList<>();
        this.integrity = 100;
        this.formation = FormationType.LINE;
    }

    public void addGoose(Goose g) {
        geese.add(g);
    }

    public void organize() {
        int count = geese.size();

        if (count < 3) {
            this.formation = FormationType.LINE;
            System.out.println("Гусей всего " + count + ". Летим линией.");
        } else if (count == 3) {
            this.formation = FormationType.TRIANGLE;
            System.out.println("Трое гусей построились в Треугольник.");
        } else if (count == 4) {
            this.formation = FormationType.SQUARE;
            System.out.println("Четверо гусей замкнули строй в Квадрат.");
        } else {
            this.formation = FormationType.POLYGON;
            System.out.println("Стая большая (" + count + "), построена сложная фигура (Многоугольник).");
        }
    }

    @Override
    public void defend(int damage) {
        System.out.println("Стая держит строй: " + formation);

        double damageMultiplier = 1.0;

        switch (formation) {
            case TRIANGLE:
                damageMultiplier = 0.8;
                break;
            case SQUARE:
                damageMultiplier = 0.7;
                break;
            case POLYGON:
                damageMultiplier = 0.6;
                break;
            default:
                damageMultiplier = 1.0;
        }

        int effectiveDamage = (int) (damage * damageMultiplier);
        this.integrity -= effectiveDamage;

        for (Goose g : geese) {
            g.defend(effectiveDamage / Math.max(1, geese.size()));
        }

        if (this.integrity < 50 && this.formation != FormationType.SCATTERED) {
            this.formation = FormationType.SCATTERED;
            System.out.println("! ВЕТЕР РАЗРУШИЛ ИХ ФИГУРУ !");
        }
    }

    public void checkFormation() throws FormationBrokenException {
        if (this.formation == FormationType.SCATTERED && integrity < 30) {
            throw new FormationBrokenException("Строй полностью разрушен! Гуси разлетаются!");
        }
    }

    @Override
    public boolean isDefeated() {
        return formation == FormationType.SCATTERED;
    }

    public FormationType getFormation() {
        return formation;
    }
}