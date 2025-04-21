package ru.p3xi.cm;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import ru.p3xi.labwork.*;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Класс модели коллекции, служит оболкой над ней
 */
@JsonIgnoreProperties({ "size" })
public class Model {
    /** Коллекция лабораторных работ */
    private TreeSet<LabWork> labs;
    /** Время создания */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime creationDate;
    /** Поле для создание новых id */
    private long id;

    public Model() {
        labs = new TreeSet<LabWork>();
        creationDate = LocalDateTime.now();
    }

    /**
     * Получить все  лабораторные работы
     * @return
     */
    public ArrayList<LabWork> getLabWorks() {
        ArrayList<LabWork> all = new ArrayList<>();
        for (LabWork labWork : labs) {
            all.add(labWork);
        }
        return all;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setLabWorks(ArrayList<LabWork> labWorks) {
        labs = new TreeSet<LabWork>(labWorks);
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id++;
    }

    public int getSize() {
        return labs.size();
    }

    /**
     * Добавить новую лабораторную работу
     * @param labWork
     */
    public void add(LabWork labWork) {
        labWork.setId(getId());
        labs.add(labWork);
    }

    /**
     * Получить лабораторную работу по id
     * @param id
     * @return
     */
    public LabWork getById(long id) {
        for (LabWork labWork : labs) {
            if (labWork.getId() == id)
                return labWork;
        }
        return null;
    }

    /**
     * Обновить лабораторную работу по id
     * @param id
     * @param labWork
     * @return
     */
    public boolean update(long id, LabWorkBuilder labWork) {
        for (LabWork labWorkNew : labs) {
            if (labWorkNew.getId() == id) {
                try {
                    labWorkNew.setName(labWork.getName());
                    labWorkNew.setCoordinates(labWork.getCoordinates());
                    labWorkNew.setMinimalPoint(labWork.getMinimalPoint());
                    labWorkNew.setDifficulty(labWork.getDifficulty());
                    labWorkNew.setDiscipline(labWork.getDiscipline());
                } catch (Exception e) {
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Удалить лабораторную работу по id
     * @param id
     */
    public void remove(long id) {
        for (LabWork labWork : labs) {
            if (labWork.getId() == id) {
                labs.remove(labWork);
                return;
            }
        }
    }

    /**
     * Очистить коллекцию
     */
    public void clear() {
        labs.clear();
    }

    /**
     * Удалить все лабораторные работы со сложностью diff
     * @param diff
     */
    public void removeByDiff(Difficulty diff) {
        TreeSet<LabWork> labsIter = (TreeSet<LabWork>) labs.clone();
        for (LabWork labWork : labsIter) {
            if (labWork.getDifficulty().equals(diff)) {
                labs.remove(labWork);
            }
        }
    }

    /**
     * Удалит все лабораторные работы, меньше данной
     * @param exLabWork
     */
    public void removeLower(LabWork exLabWork) {
        while (true) {
            LabWork labWork = labs.lower(exLabWork);
            if (labWork == null)
                break;
            labs.remove(labWork);
        }
    }

    /**
     * Добавить элемент в коллекцию, если он будет минимальным
     * @param labWork
     * @return
     */
    public boolean addIfMax(LabWork labWork) {
        if (labs.higher(labWork) == null) {
            labs.add(labWork);
            return true;
        } else
            return false;
    }

    /**
     * Добавить элемент в коллекцию, если он будет максимальным
     * @param labWork
     * @return
     */
    public boolean addIfMin(LabWork labWork) {
        if (labs.lower(labWork) == null) {
            labs.add(labWork);
            return true;
        } else
            return false;
    }

    /**
     * Сохранить коллекцию в файл
     * @param filename
     */
    public void save(String filename) {
        FileWriter fw;
        String fileContent;

        try {
            XmlMapper mapper = new XmlMapper();
            mapper.findAndRegisterModules();
            fileContent = mapper.writeValueAsString(this);
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        try {
            fw = new FileWriter(filename, false);
            fw.write(fileContent);
            fw.flush();
        } catch (IOException e) {
            System.out.println(e);
            return;
        }
    }

    /**
     * Загрузить коллекцию из файла
     * @param filename
     * @return
     */
    public static Model load(String filename) {
        FileInputStream obj;
        String fileContent;
        try {
            obj = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return new Model();
        } catch (NullPointerException e) {
            System.out.println("Файл не найден");
            return null;
        }
        try {
            fileContent = new String(new BufferedInputStream(obj).readAllBytes());
        } catch (IOException e) {
            System.out.println(e);
            return new Model();
        }
        try {
            XmlMapper mapper = new XmlMapper();
            mapper.findAndRegisterModules();
            return mapper.readValue(fileContent, Model.class);
        } catch (Exception e) {
            System.out.println("Не удалось правильно интрепретировать файл, используется новая коллекция");
            return new Model();
        }
    }
}
