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
import java.util.concurrent.locks.ReentrantReadWriteLock;
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
    private final ReentrantReadWriteLock lock;

    public Model() {
        labs = new TreeSet<LabWork>();
        creationDate = LocalDateTime.now();
        lock = new ReentrantReadWriteLock();
    }

    /**
     * Получить все лабораторные работы
     * 
     * @return
     */
    public ArrayList<LabWork> getLabWorks() {
        lock.readLock().lock();
        try{
            ArrayList<LabWork> all = new ArrayList<>();
            for (LabWork labWork : labs) {
                all.add(labWork);
            }
            return all;
        } finally {
            lock.readLock().unlock();
        }
    }

    public LocalDateTime getCreationDate() {
        lock.readLock().lock();
        try{
            return creationDate;
        } finally {
            lock.readLock().unlock();
        }
    }

    public void setLabWorks(ArrayList<LabWork> labWorks) {
        lock.writeLock().lock();
        try{
            labs = new TreeSet<LabWork>(labWorks);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void setCreationDate(LocalDateTime creationDate) {
        lock.writeLock().lock();
        try{
            this.creationDate = creationDate;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void setId(long id) {
        lock.writeLock().lock();
        try{
            this.id = id;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void setFile(String filename) {
        lock.writeLock().lock();
        try{
            this.file = new FileContainer(filename);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public long getId() {
        lock.readLock().lock();
        try{
            return id++;
        } finally {
            lock.readLock().unlock();
        }
    }

    public int getSize() {
        lock.readLock().lock();
        try{
            return labs.size();
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Добавить новую лабораторную работу
     * 
     * @param labWork
     */
    public void add(LabWork.Builder labWork) {
        lock.writeLock().lock();
        try{
            labWork.setId(getId());
            labs.add(labWork.build());
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Получить лабораторную работу по id
     * 
     * @param id
     * @return
     */
    public LabWork getById(long id) {
        lock.readLock().lock();
        try{
            return labs.stream().filter(x -> x.getId() == id).findAny().get();
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Обновить лабораторную работу по id
     * 
     * @param id
     * @param labWork
     * @return
     */
    public boolean update(long id, LabWork.Builder labWork) {
        lock.writeLock().lock();
        try{
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
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Удалить лабораторную работу по id
     * 
     * @param id
     */
    public void remove(long id) {
        lock.writeLock().lock();
        try{
            labs.remove(labs.stream().filter(x -> x.getId() == id).findAny().get());
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Очистить коллекцию
     */
    public void clear() {
        lock.writeLock().lock();
        try{
            labs.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Удалить все лабораторные работы со сложностью diff
     * 
     * @param diff
     */
    public void removeByDiff(Difficulty diff) {
        lock.writeLock().lock();
        try{
            labs = labs.stream()
                .filter(x -> x.getDifficulty()
                        .equals(diff))
                .collect(Collectors.toCollection(() -> new TreeSet<LabWork>()));
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Удалит все лабораторные работы, меньше данной
     * 
     * @param exLabWork
     */
    public void removeLower(LabWork exLabWork) {
        lock.writeLock().lock();
        try{
            while (true) {
                LabWork labWork = labs.lower(exLabWork);
                if (labWork == null)
                    break;
                labs.remove(labWork);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Добавить элемент в коллекцию, если он будет минимальным
     * 
     * @param labWork
     * @return
     */
    public boolean addIfMax(LabWork labWork) {
        lock.writeLock().lock();
        try{
            if (labs.higher(labWork) == null) {
                labs.add(labWork);
                return true;
            } else
                return false;
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Добавить элемент в коллекцию, если он будет максимальным
     * 
     * @param labWork
     * @return
     */
    public boolean addIfMin(LabWork labWork) {
        lock.writeLock().lock();
        try{
            if (labs.lower(labWork) == null) {
                labs.add(labWork);
                return true;
            } else
                return false;
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Сохранить коллекцию в файл
     * 
     * @param filename
     */
    public void save(String filename) {
        lock.writeLock().lock();
        try{
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
        } finally {
            lock.writeLock().unlock();
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
