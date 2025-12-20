package Ability;

import java.util.List;
import Characters.live.Goose;

public interface Ability {
    boolean use(List<Goose> squad);
}