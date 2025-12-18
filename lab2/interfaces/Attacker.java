package lab2.interfaces;

import lab2.exceptions.FormationBrokenException;

public interface Attacker {
    void attack(Defender target) throws FormationBrokenException;
}