package ru.p3xi.cm;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import ru.p3xi.labwork.*;

public class Model {
    private TreeSet<LabWork> labs;
    private LocalDateTime creationDate;

    public Model() {
        labs = new TreeSet<LabWork>();
        creationDate = LocalDateTime.now();
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void add(LabWork labWork) {
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

    public boolean update(long id, LabWork labWork) {
        Iterator<LabWork> iterator = labs.iterator();
        while (iterator.hasNext()) {
            LabWork labWorkNew = iterator.next();
            if (labWorkNew.getId() == id) {
                labWorkNew.update(labWork.getName(), labWork.getCoordinates(), labWork.getMinimalPoint(),
                        labWork.getDifficulty(), labWork.getDiscipline());
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

    public ArrayList<LabWork> getAll() {
        ArrayList<LabWork> all = new ArrayList<>();
        Iterator<LabWork> iterator = labs.iterator();
        while (iterator.hasNext()) {
            all.add(iterator.next());
        }
        return all;
    }
}
