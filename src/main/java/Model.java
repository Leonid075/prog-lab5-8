import java.util.Iterator;
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
            if (labWork.getId() == id){
                labs.remove(labWork);
                return;
            }
        }
    }

    public void clear() {
        labs.clear();
    }

}
