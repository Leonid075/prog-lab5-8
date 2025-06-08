package ru.p3xi.labwork;

import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Класс координат
 */
@JsonDeserialize(builder = Coordinates.Builder.class)
public class Coordinates implements Showable, Serializable {
    private Integer x; // Максимальное значение поля: 970, Поле не может быть null
    private Float y; // Максимальное значение поля: 721, Поле не может быть null

    Coordinates(Builder builder) {
        setX(builder.x);
        setY(builder.y);
    }

    private void setX(Integer x) {
        this.x = x;
    }

    private void setY(Float y) {
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    /**
     * Заполнение элемента в консоли
     * 
     * @param con
     * @throws FileEndException
     */
    public static Coordinates buildInTerminal(VirtualConsole con) throws FileEndException {
        Builder builder = new Builder();
        Integer x;
        BigDecimal y;
        con.writeLine("| Введите координаты:");
        while (true) {
            try {
                String input = con.readLine("| | x (макс 970): ");
                if (input.equals(""))
                    x = null;
                else
                    x = Integer.parseInt(input);
                builder = builder.setX(x);
                break;
            } catch (NumberFormatException | ValueException e) {
                System.out.println(e);
            }
        }
        while (true) {
            try {
                String input = con.readLine("| | y (макс 721): ");
                if (input.equals(""))
                    y = null;
                else
                    y = new BigDecimal(input);
                builder = builder.setY(y);
                break;
            } catch (NumberFormatException | ValueException e) {
                System.out.println(e);
            }
        }
        try {
            return builder.build();
        } catch (ValueException e) {
            System.out.println(e);
            return null;
        }
    }

    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "set")
    public static class Builder {
        private Integer x;
        private Float y;

        public Builder setX(Integer x) throws ValueException {
            if (x == null)
                throw new ValueException("x не может быть null");
            if (x > 970)
                throw new ValueException("x должен быть не больше 970");
            this.x = x;
            return this;
        }

        public Builder setY(BigDecimal y) throws ValueException {
            if (y == null)
                throw new ValueException("y не может быть null");
            if (y.compareTo(new BigDecimal("721"))>0)
                throw new ValueException("y должен быть не больше 721");
            this.y = Float.parseFloat(y.toString());
            return this;
        }

        public Coordinates build() throws ValueException {
            return new Coordinates(this);
        }
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

    @Override
    public String show() {
        return ("| | Координата x: " + getX() + "\n" +
                "| | Координата y: " + getY());
    }
}
