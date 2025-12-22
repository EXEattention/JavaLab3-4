package Exceptions;

public class FormationException extends Exception {

    public FormationException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Группа разлетелась: " + super.getMessage();
    }
}