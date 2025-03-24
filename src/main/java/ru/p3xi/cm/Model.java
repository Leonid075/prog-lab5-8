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

public class Model {
    private TreeSet<LabWork> labs;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime creationDate;
    private long id;

    public Model() {
        labs = new TreeSet<LabWork>();
        creationDate = LocalDateTime.now();
    }

    public ArrayList<LabWork> getLabWorks() {
        ArrayList<LabWork> all = new ArrayList<>();
        Iterator<LabWork> iterator = labs.iterator();
        while (iterator.hasNext()) {
            all.add(iterator.next());
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

    public void add(LabWork labWork) {
        labWork.setId(getId());
        labs.add(labWork);
    }

    public LabWork getById(long id) {
        Iterator<LabWork> iterator = labs.iterator();
        while (iterator.hasNext()) {
            LabWork labWork = iterator.next();
            if (labWork.getId() == id)
                return labWork;
        }
        return null;
    }

    public boolean update(long id, LabWorkBuilder labWork) {
        Iterator<LabWork> iterator = labs.iterator();
        while (iterator.hasNext()) {
            LabWork labWorkNew = iterator.next();
            if (labWorkNew.getId() == id) {
                try {
                    labWorkNew.setName(labWork.getName());
                    labWorkNew.setCoordinates(labWork.getCoordinates());
                    labWorkNew.setMinimalPoint(labWork.getMinimalPoint());
                    labWorkNew.setDifficulty(labWork.getDifficulty());
                    labWorkNew.setDiscipline(labWork.getDiscipline());
                }
                catch (Exception e) {}
                return true;
            }
        }
        return false;
    }

    public void remove(long id) {
        Iterator<LabWork> iterator = labs.iterator();
        while (iterator.hasNext()) {
            LabWork labWork = iterator.next();
            if (labWork.getId() == id) {
                labs.remove(labWork);
                return;
            }
        }
    }

    public void clear() {
        labs.clear();
    }

    public void removeByDiff(Difficulty diff) {
        for (LabWork labWork : labs) {
            if (labWork.getDifficulty().equals(diff)) {
                labs.remove(labWork);
            }
        }
    }

    public void removeLower(LabWork exLabWork) {
        while (true) {
            LabWork labWork = labs.lower(exLabWork);
            if (labWork == null)
                break;
            labs.remove(labWork);
        }
    }

    public boolean addIfMax(LabWork labWork) {
        if (labs.higher(labWork) == null) {
            labs.add(labWork);
            return true;
        } else
            return false;
    }

    public boolean addIfMin(LabWork labWork) {
        if (labs.lower(labWork) == null) {
            labs.add(labWork);
            return true;
        } else
            return false;
    }

    public void save(String filename) {
        FileWriter fw;
        String fileContent;

        try {
            XmlMapper mapper = new XmlMapper();
            mapper.findAndRegisterModules();
            fileContent = mapper.writeValueAsString(this);
            System.out.println(fileContent);
        }
        catch (Exception e) {
            System.out.println(e);
            return;
        }
        try {
            fw = new FileWriter(filename, false);
            fw.write(fileContent);
            fw.flush();
        }
        catch (IOException e) {
            System.out.println(e);
            return;
        }
    }

    public static Model load(String filename) {
        FileInputStream obj;
        String fileContent;
        try {
            obj = new FileInputStream(filename);
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
            return new Model();
        }
        try {
            fileContent = new String(new BufferedInputStream(obj).readAllBytes());
            System.out.println(fileContent);
        }
        catch (IOException e) {
            System.out.println(e);
            return new Model();
        }
        try {
            XmlMapper mapper = new XmlMapper();
            mapper.findAndRegisterModules();
            return mapper.readValue(fileContent, Model.class);
        }
        catch (Exception e) {
            System.out.println(e);
            return new Model();
        }
    }
}
