import ru.p3xi.cm.Model;
import ru.p3xi.labwork.*;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Main {
    public static void main(String[] args) {
        try {
            call();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void call() throws Exception {
        LabWork labWork = new LabWork("123", new Coordinates(1, 1.2f), 0.1f, Difficulty.EASY, new Discipline("321", 321, (long) 3, 2));
        // Coordinates cords = ;

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
    	String jsonBook = mapper.writeValueAsString(labWork);
    	System.out.println(jsonBook);

        ObjectMapper mapper1 = new ObjectMapper();
        mapper1.findAndRegisterModules();
        LabWork labWork1 = mapper1.readValue(jsonBook, LabWork.class);
        System.out.println(labWork1.toString());

    }
}