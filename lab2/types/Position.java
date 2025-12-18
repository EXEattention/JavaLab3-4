package lab2.types;

public record Position(double x, double y, double altitude) {
    public Position changeAltitude(double delta) {
        return new Position(x, y, Math.max(0, altitude + delta));
    }
}