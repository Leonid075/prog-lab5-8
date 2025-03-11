package ru.p3xi.labwork;

import java.time.LocalDateTime;

public class LabWork implements Comparable<LabWork> {
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
            Discipline discipline) throws ValueException {
        setId(0);
        setName(name);
        setCoordinates(coordinates);
        setCreationDate(LocalDateTime.now());
        setMinimalPoint(minimalPoint);
        setDifficulty(difficulty);
        setDiscipline(discipline);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) throws ValueException {
        if (name.equals(""))
            throw new ValueException("Name не может быть пустым");
        if (name.equals(null))
            throw new ValueException("Name не может быть null");
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) throws ValueException {
        if (coordinates.equals(null))
            throw new ValueException("coordinates не может быть null");
        this.coordinates = coordinates;
    }

    public void setCreationDate(LocalDateTime creationDate) throws ValueException {
        if (creationDate.equals(null))
            throw new ValueException("creationDate не может быть null");
        this.creationDate = creationDate;
    }

    public void setMinimalPoint(float minimalPoint) throws ValueException {
        if (minimalPoint <= 0)
            throw new ValueException("minimalPoint должен быть больше 0");
        this.minimalPoint = minimalPoint;
    }

    public void setDifficulty(Difficulty difficulty) throws ValueException {
        if (difficulty.equals(null))
            throw new ValueException("difficulty не может быть null");
        this.difficulty = difficulty;
    }

    public void setDiscipline(Discipline discipline) throws ValueException {
        if (discipline.equals(null))
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
