package ru.p3xi.labwork;

import java.io.Console;
import java.io.Serializable;
import java.time.LocalDateTime;

import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Класс лабораторной работы
 */
@JsonDeserialize(builder = LabWork.Builder.class)
public class LabWork implements Comparable<LabWork>, Showable, Serializable {
    /**
     * Значение поля должно быть больше 0, Значение этого поля должно быть
     * уникальным, Значение этого поля должно генерироваться автоматически
     */
    protected long id;
    /** Поле не может быть null, Строка не может быть пустой */
    protected String name;
    /** Поле не может быть null */
    protected Coordinates coordinates;
    /**
     * Поле не может быть null, Значение этого поля должно генерироваться
     * автоматически
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    protected LocalDateTime creationDate;
    /** Значение поля должно быть больше 0 */
    protected float minimalPoint;
    /** Поле может быть null */
    protected Difficulty difficulty;
    /** Поле может быть null */
    protected Discipline discipline;
    protected String owner;

    public LabWork(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setCoordinates(builder.coordinates);
        setCreationDate(builder.creationDate);
        setMinimalPoint(builder.minimalPoint);
        setDifficulty(builder.difficulty);
        setDiscipline(builder.discipline);
        setOwner(builder.owner);
    }

    private void setId(long id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    private void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    private void setMinimalPoint(float minimalPoint) {
        this.minimalPoint = minimalPoint;
    }

    private void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    private void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    private void setOwner(String owner) {
        this.owner = owner;
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

    public String getOwner() {
        return owner;
    }

    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "set")
    public static class Builder implements Serializable {
        private long id;
        private String name;
        private Coordinates coordinates;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime creationDate;
        private float minimalPoint;
        private Difficulty difficulty;
        private Discipline discipline;
        private String owner;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) throws ValueException {
            if (name == null)
                throw new ValueException("Name не может быть null");
            if (name.equals(""))
                throw new ValueException("Name не может быть пустым");
            this.name = name;
            return this;
        }

        public Builder setCoordinates(Coordinates coordinates) throws ValueException {
            if (coordinates == null)
                throw new ValueException("coordinates не может быть null");
            this.coordinates = coordinates;
            return this;
        }

        public Builder setCreationDate(LocalDateTime creationDate) throws ValueException {
            if (creationDate == null)
                throw new ValueException("creationDate не может быть null");
            this.creationDate = creationDate;
            return this;
        }

        public Builder setMinimalPoint(float minimalPoint) throws ValueException {
            if (minimalPoint <= 0)
                throw new ValueException("minimalPoint должен быть больше 0");
            this.minimalPoint = minimalPoint;
            return this;
        }

        public Builder setDifficulty(Difficulty difficulty) throws ValueException {
            if (difficulty == null)
                throw new ValueException("difficulty не может быть null");
            this.difficulty = difficulty;
            return this;
        }

        public Builder setDiscipline(Discipline discipline) throws ValueException {
            if (discipline == null)
                throw new ValueException("discipline не может быть null");
            this.discipline = discipline;
            return this;
        }

        public Builder setOwner(String owner) {
            this.owner = owner;
            return this;
        }

        public LabWork build() {
            return new LabWork(this);
        }
    }

    /**
     * Заполнение элемента в консоли
     * 
     * @param con
     * @throws FileEndException
     */
    public static LabWork.Builder buildInTerminal(VirtualConsole con) throws FileEndException {
        Builder builder = new Builder();
        Coordinates coordinates;
        Float minimalPoint;
        Discipline discipline;
        con.writeLine("Введите лабораторную работу:");
        while (true) {
            try {
                String input = con.readLine("| name: ");
                if (input.equals(""))
                    input = null;
                builder = builder.setName(input);
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
                    minimalPoint = Float.parseFloat(input);
                builder = builder.setMinimalPoint(minimalPoint.floatValue());
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
                builder = builder.setDifficulty(Difficulty.valueOf(input));
                break;
            } catch (IllegalArgumentException | ValueException e) {
                System.out.println("Доступны занчения VERY_EASY, EASY, HOPELESS");
            }
        }
        discipline = Discipline.buildInTerminal(con);
        try {
            builder = builder.setCoordinates(coordinates);
            builder = builder.setCreationDate(LocalDateTime.now());
            builder = builder.setDiscipline(discipline);
        } catch (Exception e) {
            System.out.println(e);
        }
        return builder;
    }

    @Override
    public String toString() {
        return ((Long) id).toString() + "_" + name + "_" + creationDate.toString() + "_"
                + discipline.toString() + "_" + difficulty.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj.getClass() != this.getClass())
            return false;
        final LabWork labWork = (LabWork) obj;
        if (labWork.getId() != this.id)
            return false;
        if (!labWork.getName().equals(this.name))
            return false;
        if (!labWork.getCoordinates().equals(this.coordinates))
            return false;
        if (!labWork.getCreationDate().equals(this.creationDate))
            return false;
        if (labWork.getMinimalPoint() != this.minimalPoint)
            return false;
        if (!labWork.getDifficulty().equals(this.difficulty))
            return false;
        if (!labWork.getDiscipline().equals(this.discipline))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return (int) id * (name.hashCode() + coordinates.hashCode() + creationDate.hashCode() + (int) minimalPoint
                + difficulty.hashCode() + discipline.hashCode());
    }

    @Override
    public int compareTo(LabWork labWork) {
        return this.toString().compareTo(labWork.toString());
    }

    @Override
    public String show() {
        return ("Лабораторная работа:\n" +
                "| Название: " + getName() + "\n" +
                "| ID:" + getId() + "\n" +
                "| Координаты:\n" + coordinates.show() + "\n" +
                "| Дата создания: " + getCreationDate().toString() + "\n" +
                "| minimalPoint: " + getMinimalPoint() + "\n" +
                "| difficulty: " + getDifficulty().toString() + "\n" +
                "| Дисциплина:\n" + discipline.show());
    }
}
