package ru.p3xi.cm;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import ru.p3xi.file.FileContainer;
import ru.p3xi.file.FileException;
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
    private FileContainer file;

    public Model() {
        labs = new TreeSet<LabWork>();
        creationDate = LocalDateTime.now();
    }

    /**
     * Получить все лабораторные работы
     * 
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

    public void setFile(String filename) {
        this.file = new FileContainer(filename);
    }

    public long getId() {
        return id++;
    }

    public int getSize() {
        return labs.size();
    }

    /**
     * Добавить новую лабораторную работу
     * 
     * @param labWork
     */
    public void add(LabWork.Builder labWork) {
        labWork.setId(getId());
        labs.add(labWork.build());
    }

    /**
     * Получить лабораторную работу по id
     * 
     * @param id
     * @return
     */
    public LabWork getById(long id) {
        return labs.stream().filter(x -> x.getId() == id).findAny().get();
    }

    /**
     * Обновить лабораторную работу по id
     * 
     * @param id
     * @param labWork
     * @return
     */
    public boolean update(long id, LabWork.Builder labWork) {
        Optional<LabWork> oldLabWork = labs.stream().filter(x -> x.getId() == id).findAny();
        if (labWork == null || oldLabWork == null)
            return false;
        try {
            labs.remove(oldLabWork.get());
            labs.add(labWork.setId(id).build());
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * Удалить лабораторную работу по id
     * 
     * @param id
     */
    public void remove(long id) {
        labs.remove(labs.stream().filter(x -> x.getId() == id).findAny().get());
    }

    /**
     * Очистить коллекцию
     */
    public void clear() {
        labs.clear();
    }

    /**
     * Удалить все лабораторные работы со сложностью diff
     * 
     * @param diff
     */
    public void removeByDiff(Difficulty diff) {
        labs = labs.stream()
                .filter(x -> x.getDifficulty()
                        .equals(diff))
                .collect(Collectors.toCollection(() -> new TreeSet<LabWork>()));
    }

    /**
     * Удалит все лабораторные работы, меньше данной
     * 
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
     * 
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
     * 
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
     * 
     * @param filename
     */
    public void save(String filename) {
        FileWriter fw;
        String content;

        try {
            XmlMapper mapper = new XmlMapper();
            mapper.findAndRegisterModules();
            content = mapper.writeValueAsString(this);
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        try {
            file.writeFile(content);
        } catch (FileException e) {
            System.out.println("Ошибка чтения файла");
        }
    }

    /**
     * Загрузить коллекцию из файла
     * 
     * @param filename
     * @return
     */
    public static Model load(String filename) {
        String content;
        try {
            content = new FileContainer(filename).readFile();
        } catch (FileException | NullPointerException e) {
            System.out.println("Ошибка чтения файла");
            return null;
        }
        try {
            XmlMapper mapper = new XmlMapper();
            mapper.findAndRegisterModules();
            Model model = mapper.readValue(content, Model.class);
            model.setFile(filename);
            return model;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Не удалось правильно интрепретировать файл, используется новая коллекция");
            Model model = new Model();
            model.setFile(filename);
            return model;
        }
    }
}
