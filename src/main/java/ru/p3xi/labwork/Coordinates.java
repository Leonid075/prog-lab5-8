package ru.p3xi.labwork;

public class Coordinates extends CoordinatesBuilder {
    // private Integer x; // Максимальное значение поля: 970, Поле не может быть null
    // private Float y; // Максимальное значение поля: 721, Поле не может быть null

    public Coordinates() {
    }

    public Coordinates(CoordinatesBuilder cb) throws ValueException {
        setX(cb.getX());
        setY(cb.getY());
    }

    @Override
    public String toString() {
        return "Coords" + x.toString() + "_" + y.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj.getClass() != this.getClass())
            return false;
        final Coordinates discipline = (Coordinates) obj;
        if (!discipline.getX().equals(this.x))
            return false;
        if (!discipline.getY().equals(this.y))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return x.hashCode() + y.hashCode();
    }
}
