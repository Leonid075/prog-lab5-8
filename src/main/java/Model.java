import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import ru.p3xi.labwork.*;

public class Model {
    private TreeSet<LabWork> labs;

    public Model() {
        labs = new TreeSet<LabWork>();
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

    public boolean update(long id, String name, Coordinates coordinates, float minimalPoint, Difficulty difficulty,
            Discipline discipline) {
        Iterator<LabWork> iterator = labs.iterator();
        while (iterator.hasNext()) {
            LabWork labWork = iterator.next();
            if (labWork.getId() == id) {
                labWork.update(name, coordinates, minimalPoint, difficulty, discipline);
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

    public float averageMinimalPoint() {
        float average = 0;
        for (LabWork labWork : labs) {
            average += labWork.getMinimalPoint();
        }
        return average / labs.size();
    }

    public boolean addIfMax(LabWork labWork) {
        if(labs.higher(labWork) == null) {
            labs.add(labWork);
            return true;
        }
        else return false;
    }

    public boolean addIfMin(LabWork labWork) {
        if(labs.lower(labWork) == null) {
            labs.add(labWork);
            return true;
        }
        else return false;
    }

    public List<Discipline> getDisciplines() {
        ArrayList<Discipline> disciplines = new ArrayList<>();
        Iterator<LabWork> iterator = labs.iterator();
        while (iterator.hasNext()) {
            LabWork labWork = iterator.next();
            disciplines.add(labWork.getDiscipline());
        }
        return disciplines.reversed();
    }
}
