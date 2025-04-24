package ru.p3xi.labwork;

import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Класс предмета
 */
@JsonDeserialize(builder = Discipline.Builder.class)
public class Discipline implements Showable {
    private String name; // Поле не может быть null, Строка не может быть пустой
    private long lectureHours;
    private Long practiceHours; // Поле не может быть null
    private Integer labsCount; // Поле может быть null

    public Discipline(Builder builder) {
        setName(builder.name);
        setLectureHours(builder.lectureHours);
        setLabsCount(builder.labsCount);
        setPracticeHours(builder.practiceHours);
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setLectureHours(long lectureHours) {
        this.lectureHours = lectureHours;
    }

    private void setPracticeHours(Long practiceHours) {
        this.practiceHours = practiceHours;
    }

    private void setLabsCount(Integer labsCount) {
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

    /**
     * Заполнение элемента в консоли
     * 
     * @param con
     * @throws FileEndException
     */
    public static Discipline buildInTerminal(VirtualConsole con) throws FileEndException {
        Builder builder = new Builder();
        Long lectureHours;
        Long practiceHours;
        Integer labsCount;
        con.writeLine("| Введите дисциплину:");
        while (true) {
            try {
                String input = con.readLine("| | name: ");
                if (input.equals(""))
                    input = null;
                builder = builder.setName(input);
                break;
            } catch (NumberFormatException | ValueException e) {
                System.out.println(e);
            }
        }
        while (true) {
            try {
                String input = con.readLine("| | lectureHours: ");
                if (input.equals(""))
                    lectureHours = 0l;
                else
                    lectureHours = Long.parseLong(input);
                builder = builder.setLectureHours(lectureHours.longValue());
                break;
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        }
        while (true) {
            try {
                String input = con.readLine("| | practiceHours: ");
                if (input.equals(""))
                    practiceHours = null;
                else
                    practiceHours = Long.parseLong(input);
                builder = builder.setPracticeHours(practiceHours);
                break;
            } catch (NumberFormatException | ValueException e) {
                System.out.println(e);
            }
        }
        while (true) {
            try {
                String input = con.readLine("| | labsCount: ");
                if (input.equals(""))
                    labsCount = null;
                else
                    labsCount = Integer.parseInt(input);
                builder = builder.setLabsCount(labsCount);
                break;
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        }
        try {
            return builder.build();
        } catch (ValueException e) {
            System.out.println(e);
            return null;
        }
    }

    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "set")
    public static class Builder {
        private String name; // Поле не может быть null, Строка не может быть пустой
        private long lectureHours;
        private Long practiceHours; // Поле не может быть null
        private Integer labsCount; // Поле может быть null

        public Builder setName(String name) throws ValueException {
            if (name == null)
                throw new ValueException("name не может быть null");
            this.name = name;
            return this;
        }

        public Builder setLectureHours(long lectureHours) {
            this.lectureHours = lectureHours;
            return this;
        }

        public Builder setPracticeHours(Long practiceHours) throws ValueException {
            if (practiceHours == null)
                throw new ValueException("practiceHours не может быть null");
            this.practiceHours = practiceHours;
            return this;
        }

        public Builder setLabsCount(Integer labsCount) {
            this.labsCount = labsCount;
            return this;
        }

        public Discipline build() throws ValueException {
            return new Discipline(this);
        }
    }

    @Override
    public String toString() {
        return name + "_" + ((Long) lectureHours).toString() + "_" + practiceHours.toString() + "_"
                + String.valueOf(labsCount);
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

    @Override
    public String show() {
        return ("| | Название дисциплины: " + getName() + "\n" +
                "| | lectureHours: " + getLectureHours() + "\n" +
                "| | practiceHours: " + getPracticeHours() + "\n" +
                "| | labsCount: " + getLabsCount());
    }
}
