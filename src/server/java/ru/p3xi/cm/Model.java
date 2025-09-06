package ru.p3xi.cm;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.TreeSet;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import ru.p3xi.labwork.*;

/**
 * Класс модели коллекции, служит оболкой над ней
 */
public class Model {
    /** Коллекция лабораторных работ */
    private TreeSet<LabWork> labs;
    /** Словарь юзеров и паролей */
    private HashMap<String, String> users;
    /** Время создания */
    private LocalDateTime creationDate;
    private final ReentrantReadWriteLock lock;
    private DBManager dbManager;

    public Model() {
        labs = new TreeSet<LabWork>();
        users = new HashMap<>();
        creationDate = LocalDateTime.now();
        lock = new ReentrantReadWriteLock();
        try {
            dbManager = new DBManager();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public String SHA_512(String passwordToHash, String salt){
    String generatedPassword = null;
    try {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        generatedPassword = sb.toString();
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    }
    return generatedPassword;
}


    public void addUser(String username, String password) {
        try {
            dbManager.addUser(username, SHA_512(password, "salt"));
            users.put(username, SHA_512(password, "salt"));
        } catch (SQLException e) {}
    }

    public boolean userExist(String username) {
        if (users.get(username) != null)
            return true;
        return false;
    }

    public boolean comparePassword(String username, String password) {
        if (users.get(username) == null) return false;
        if (users.get(username).equals(SHA_512(password, "salt")))
            return true;
        return false;
    }

    private void setUsers(HashMap<String, String> users) {
        this.users = new HashMap<>(users);
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
            Long id = dbManager.addLabWork(labWork.build());
            labWork.setId(id);
            labs.add(labWork.build());
        } catch (SQLException e) {
            System.out.println(e);
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
                dbManager.updateLabWork(labWork.setId(id).build());
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
            dbManager.deleteLabWork(id);
            labs.remove(labs.stream().filter(x -> x.getId() == id).findAny().get());
        } catch(SQLException e) {} finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Очистить коллекцию
     */
    public void clear(String username) {
        lock.writeLock().lock();
        try{
            try {
                dbManager.deleteAllByOwner(username);
                labs = labs.stream()
                    .filter(x -> !x.getOwner()
                            .equals(username))
                    .collect(Collectors.toCollection(() -> new TreeSet<LabWork>()));
            } catch (SQLException e) {}
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
                .filter(x -> !x.getDifficulty()
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
                try {
                    dbManager.deleteLabWork(labWork.getId());
                    labs.remove(labWork);
                } catch (SQLException e) {}
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
    public boolean addIfMax(LabWork.Builder labWork) {
        lock.writeLock().lock();
        try{
            if (labs.higher(labWork.setId(0).build()) == null) {
                Long id = dbManager.addLabWork(labWork.setId(0).build());
                labs.add(labWork.setId(id).build());
                return true;
            } else
                return false;
        } catch(SQLException e) {
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
    public boolean addIfMin(LabWork.Builder labWork) {
        lock.writeLock().lock();
        try{
            if (labs.lower(labWork.setId(0).build()) == null) {
                Long id = dbManager.addLabWork(labWork.setId(0).build());
                labs.add(labWork.setId(id).build());
                return true;
            } else
                return false;
        } catch(SQLException e) {
            return false;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static Model load() {
        Model model = new Model();
        model.setCreationDate(LocalDateTime.now());
        try {
            model.setUsers(model.dbManager.getUsers());
            model.setLabWorks(model.dbManager.getLabWorks());
        } catch (SQLException e) {
            return null;
        }
        return model;
    }
}
