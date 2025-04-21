package ru.p3xi.labwork;

/**
 * Класс лабораторной работы
 */
public class LabWork extends LabWorkBuilder implements Comparable<LabWork>, Showable {
    // private long id; // Значение поля должно быть больше 0, Значение этого поля
    // должно быть
    // // уникальным, Значение этого поля должно генерироваться автоматически
    // private String name; // Поле не может быть null, Строка не может быть пустой
    // private Coordinates coordinates; // Поле не может быть null
    // private LocalDateTime creationDate; // Поле не может быть null, Значение
    // этого поля должно генерироваться
    // // автоматически
    // private float minimalPoint; // Значение поля должно быть больше 0
    // private Difficulty difficulty; // Поле может быть null
    // private Discipline discipline; // Поле может быть null

    public LabWork() {
    }

    public LabWork(LabWorkBuilder lwb) throws ValueException {
        setId(lwb.getId());
        setName(lwb.getName());
        setCoordinates(lwb.getCoordinates());
        setCreationDate(lwb.getCreationDate());
        setMinimalPoint(lwb.getMinimalPoint());
        setDifficulty(lwb.getDifficulty());
        setDiscipline(lwb.getDiscipline());
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
