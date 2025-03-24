package ru.p3xi.labwork;

import java.io.Console;

public class CoordinatesBuilder {
    protected Integer x; // Максимальное значение поля: 970, Поле не может быть null
    protected Float y; // Максимальное значение поля: 721, Поле не может быть null

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

    public void buildInTerminal(Console con) {
        Integer x;
        Float y;
        System.out.println("| Введите координаты:");
        while (true) {
            try {
                String input = con.readLine("| | x (макс 970): ");
                if (input.equals("")) x=null;
                else x = new Integer(input);
                setX(x);
                break;
            }
            catch(NumberFormatException|ValueException e) {
                System.out.println(e);
            }
        }
        while (true) {
            try {
                String input = con.readLine("| | y (макс 721): ");
                if (input.equals("")) y=null;
                else y = new Float(input);
                setY(y);
                break;
            }
            catch(NumberFormatException|ValueException e) {
                System.out.println(e);
            }
        }
    }
}
