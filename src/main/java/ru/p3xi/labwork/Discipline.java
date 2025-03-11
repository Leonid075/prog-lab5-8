package ru.p3xi.labwork;

public class Discipline {
    private String name; // Поле не может быть null, Строка не может быть пустой
    private long lectureHours;
    private Long practiceHours; // Поле не может быть null
    private Integer labsCount; // Поле может быть null

    public Discipline(String name, long lectureHours, Long practiceHours, Integer labsCount) throws ValueException {
        setName(name);
        setLectureHours(lectureHours);
        setPracticeHours(practiceHours);
        setLabsCount(labsCount);
    }

    public void setName(String name) throws ValueException {
        if(name == null) throw new ValueException("name не может быть null");
        this.name = name;
    }

    public void setLectureHours(long lectureHours) {
        this.lectureHours = lectureHours;
    }

    public void setPracticeHours(Long practiceHours) throws ValueException {
        if(practiceHours == null) throw new ValueException("name не может быть null");
        this.practiceHours = practiceHours;
    }

    public void setLabsCount(Integer labsCount) throws ValueException {
        if(labsCount == null) throw new ValueException("name не может быть null");
        this.labsCount = labsCount;
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
