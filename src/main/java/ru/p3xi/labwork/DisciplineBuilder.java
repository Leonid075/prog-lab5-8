package ru.p3xi.labwork;

public class DisciplineBuilder {
    private String name; // Поле не может быть null, Строка не может быть пустой
    private long lectureHours;
    private Long practiceHours; // Поле не может быть null
    private Integer labsCount; // Поле может быть null

    public DisciplineBuilder() {
    }

    public void setName(String name) throws ValueException {
        if (name == null)
            throw new ValueException("name не может быть null");
        this.name = name;
    }

    public void setLectureHours(long lectureHours) {
        this.lectureHours = lectureHours;
    }

    public void setPracticeHours(Long practiceHours) throws ValueException {
        if (practiceHours == null)
            throw new ValueException("name не может быть null");
        this.practiceHours = practiceHours;
    }

    public void setLabsCount(Integer labsCount) throws ValueException {
        if (labsCount == null)
            throw new ValueException("name не может быть null");
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

}
