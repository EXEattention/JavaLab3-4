package lab2.types;

public enum FormationType {
    LINE("Вытянутая линия"),
    TRIANGLE("Ровный треугольник"),
    SQUARE("Четырехугольник"),
    POLYGON("Сложный многоугольник"),
    SCATTERED("Беспорядочная куча");

    private final String description;

    FormationType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}