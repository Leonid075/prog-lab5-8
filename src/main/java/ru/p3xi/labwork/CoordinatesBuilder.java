package ru.p3xi.labwork;

public class CoordinatesBuilder {
    private Integer x; // Максимальное значение поля: 970, Поле не может быть null
    private Float y; // Максимальное значение поля: 721, Поле не может быть null

    public CoordinatesBuilder() {
    }

    public void setX(Integer x) throws ValueException {
        if (x == null)
            throw new ValueException("x не может быть null");
        if (x > 970)
            throw new ValueException("x должен быть не больше 970");
        this.x = x;
    }

    public void setY(Float y) throws ValueException {
        if (y == null)
            throw new ValueException("y не может быть null");
        if (y > 721)
            throw new ValueException("y должен быть не больше 721");
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Float getY() {
        return y;
    }
}
