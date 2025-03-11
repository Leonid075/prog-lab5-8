package ru.p3xi.labwork;

public class Coordinates {
    private Integer x; // Максимальное значение поля: 970, Поле не может быть null
    private Float y; // Максимальное значение поля: 721, Поле не может быть null

    public Coordinates(Integer x, Float y) throws ValueException {
        setX(x);
        setY(y);
    }

    public void setX(Integer x) throws ValueException {
        if (x == null) throw new ValueException("x не может быть null");
        if (x > 970) throw new ValueException("x должен быть не больше 970");
        this.x = x;
    }

    public void setY(Float y) throws ValueException {
        if (y == null) throw new ValueException("y не может быть null");
        if (y > 721) throw new ValueException("y должен быть не больше 721");
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
