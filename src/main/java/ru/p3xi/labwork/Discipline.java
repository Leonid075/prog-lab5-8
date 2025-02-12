package ru.p3xi.labwork;

public class Discipline {
    private String name; // Поле не может быть null, Строка не может быть пустой
    private long lectureHours;
    private Long practiceHours; // Поле не может быть null
    private Integer labsCount; // Поле может быть null

    public Discipline(String name, Long practiceHours, Integer labsCount) {
        this.name = name;
        this.practiceHours = practiceHours;
        this.labsCount = labsCount;
        this.lectureHours = 0;
    }

    public Discipline(String name, long lectureHours, Long practiceHours, Integer labsCount) {
        this.name = name;
        this.practiceHours = practiceHours;
        this.labsCount = labsCount;
        this.lectureHours = lectureHours;
    }

    public String getName() {
        return name;
    }

    public long getLectureHours() {
        return lectureHours;
    }

    public Long getPracticeHours() {
        return practiceHours;
    }

    public Integer getLabsCount() {
        return labsCount;
    }

    @Override
    public String toString() {
        return name + "_" + ((Long) lectureHours).toString() + "_" + practiceHours.toString() + "_"
                + labsCount.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj.getClass() != this.getClass())
            return false;
        final Discipline discipline = (Discipline) obj;
        if (!discipline.getName().equals(this.name))
            return false;
        if (!discipline.getLabsCount().equals(this.labsCount))
            return false;
        if (discipline.getLectureHours() != this.lectureHours)
            return false;
        if (!discipline.getPracticeHours().equals(this.practiceHours))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = name.hashCode();
        hash += lectureHours % 100;
        hash += practiceHours % 100;
        hash += labsCount % 100;
        return hash;
    }
}
