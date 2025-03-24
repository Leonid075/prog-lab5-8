package ru.p3xi.labwork;

import java.io.Console;

public class DisciplineBuilder {
    protected String name; // Поле не может быть null, Строка не может быть пустой
    protected long lectureHours;
    protected Long practiceHours; // Поле не может быть null
    protected Integer labsCount; // Поле может быть null

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

    public void buildInTerminal(Console con) {
        Long lectureHours;
        Long practiceHours;
        Integer labsCount;
        System.out.println("| Введите дисциплину:");
        while (true) {
            try {
                String input = con.readLine("| | name: ");
                if (input.equals("")) input=null;
                setName(input);
                break;
            }
            catch(NumberFormatException|ValueException e) {
                System.out.println(e);
            }
        }
        while (true) {
            try {
                String input = con.readLine("| | lectureHours: ");
                if (input.equals("")) lectureHours=0l;
                else lectureHours = new Long(input);
                setLectureHours(lectureHours.longValue());
                break;
            }
            catch(NumberFormatException e) {
                System.out.println(e);
            }
        }
        while (true) {
            try {
                String input = con.readLine("| | practiceHours: ");
                if (input.equals("")) practiceHours=null;
                else practiceHours = new Long(input);
                setPracticeHours(practiceHours);
                break;
            }
            catch(NumberFormatException|ValueException e) {
                System.out.println(e);
            }
        }
        while (true) {
            try {
                String input = con.readLine("| | labsCount: ");
                if (input.equals("")) labsCount=null;
                else labsCount = new Integer(input);
                setLabsCount(labsCount);
                break;
            }
            catch(NumberFormatException|ValueException e) {
                System.out.println(e);
            }
        }
    }
}
