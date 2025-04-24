package ru.p3xi.labwork;

import java.io.Console;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;

/**
 * Класс builder класса LabWork
 */
public class LabWorkBuilder {
    /** Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически */
    protected long id;
    /** Поле не может быть null, Строка не может быть пустой */
    protected String name;
    /** Поле не может быть null */
    protected Coordinates coordinates;
    /** Поле не может быть null, Значение этого поля должно генерироваться автоматически */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    protected LocalDateTime creationDate;
    /** Значение поля должно быть больше 0 */
    protected float minimalPoint;
    /** Поле может быть null */
    protected Difficulty difficulty;
    /** Поле может быть null */
    protected Discipline discipline;

    public LabWorkBuilder() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) throws ValueException {
        if (name == null)
            throw new ValueException("Name не может быть null");
        if (name.equals(""))
            throw new ValueException("Name не может быть пустым");
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) throws ValueException {
        if (coordinates == null)
            throw new ValueException("coordinates не может быть null");
        this.coordinates = coordinates;
    }

    public void setCreationDate(LocalDateTime creationDate) throws ValueException {
        if (creationDate == null)
            throw new ValueException("creationDate не может быть null");
        this.creationDate = creationDate;
    }

    public void setMinimalPoint(float minimalPoint) throws ValueException {
        if (minimalPoint <= 0)
            throw new ValueException("minimalPoint должен быть больше 0");
        this.minimalPoint = minimalPoint;
    }

    public void setDifficulty(Difficulty difficulty) throws ValueException {
        if (difficulty == null)
            throw new ValueException("difficulty не может быть null");
        this.difficulty = difficulty;
    }

    public void setDiscipline(Discipline discipline) throws ValueException {
        if (discipline == null)
            throw new ValueException("discipline не может быть null");
        this.discipline = discipline;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public float getMinimalPoint() {
        return minimalPoint;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    /**
     * Заполнение элемента в консоли
     * @param con
     * @throws FileEndException
     */
    public void buildInTerminal(VirtualConsole con) throws FileEndException {
        Coordinates coordinates;
        Float minimalPoint;
        Discipline discipline;
        con.writeLine("Введите лабораторную работу:");
        while (true) {
            try {
                String input = con.readLine("| name: ");
                if (input.equals(""))
                    input = null;
                setName(input);
                break;
            } catch (NumberFormatException | ValueException e) {
                System.out.println(e);
            }
        }
        coordinates = Coordinates.buildInTerminal(con);
        while (true) {
            try {
                String input = con.readLine("| minimalPoint: ");
                if (input.equals(""))
                    minimalPoint = 0f;
                else
                    minimalPoint = new Float(input);
                setMinimalPoint(minimalPoint.floatValue());
                break;
            } catch (NumberFormatException | ValueException e) {
                System.out.println(e);
            }
        }
        while (true) {
            try {
                String input = con.readLine("| difficulty: ");
                if (input.equals(""))
                    input = null;
                setDifficulty(Difficulty.valueOf(input));
                break;
            } catch (IllegalArgumentException | ValueException e) {
                System.out.println("Доступны занчения VERY_EASY, EASY, HOPELESS");
            }
        }
        discipline = Discipline.buildInTerminal(con);
        try {
            setCoordinates(coordinates);
            setCreationDate(LocalDateTime.now());
            setDiscipline(discipline);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
