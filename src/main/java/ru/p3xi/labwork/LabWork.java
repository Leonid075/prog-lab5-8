package ru.p3xi.labwork;

import java.time.LocalDateTime;

public class LabWork implements Comparable<LabWork> {
    private static long ids = 0;

    private long id; // Значение поля должно быть больше 0, Значение этого поля должно быть
                     // уникальным, Значение этого поля должно генерироваться автоматически
    private String name; // Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; // Поле не может быть null
    private LocalDateTime creationDate; // Поле не может быть null, Значение этого поля должно генерироваться
                                        // автоматически
    private float minimalPoint; // Значение поля должно быть больше 0
    private Difficulty difficulty; // Поле может быть null
    private Discipline discipline; // Поле может быть null

    public LabWork(String name, Coordinates coordinates, float minimalPoint, Difficulty difficulty,
            Discipline discipline) {
        this.id = ids++;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now();
        this.minimalPoint = minimalPoint;
        this.difficulty = difficulty;
        this.discipline = discipline;
    }

    public void update(String name, Coordinates coordinates, float minimalPoint, Difficulty difficulty,
            Discipline discipline) {
        this.name = name;
        this.coordinates = coordinates;
        this.minimalPoint = minimalPoint;
        this.difficulty = difficulty;
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
        final LabWork discipline = (LabWork) obj;
        if (getId() != this.id)
            return false;
        if (!discipline.getName().equals(this.name))
            return false;
        if (!discipline.getCoordinates().equals(this.coordinates))
            return false;
        if (!discipline.getCreationDate().equals(this.creationDate))
            return false;
        if (discipline.getMinimalPoint() != this.minimalPoint)
            return false;
        if (!discipline.getDifficulty().equals(this.difficulty))
            return false;
        if (!discipline.getDiscipline().equals(this.discipline))
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
        return ((Float) this.minimalPoint).compareTo((Float) labWork.getMinimalPoint());
    }
}
