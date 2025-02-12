package ru.p3xi.labwork;

public class Coordinates {
    private Integer x; // Максимальное значение поля: 970, Поле не может быть null
    private Float y; // Максимальное значение поля: 721, Поле не может быть null

    public Coordinates(Integer x, Float y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    @Override
    public String toString() {
        return x.toString() + "_" + y.toString();
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
