import ru.p3xi.cm.Model;
import ru.p3xi.commands.AddCommand;
import ru.p3xi.commands.SaveCommand;
import ru.p3xi.labwork.*;

import java.io.Console;
import java.time.LocalDateTime;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;


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
        Console con = System.console();

        Model model = Model.load(System.getenv("lab5"));

        // XmlMapper mapper1 = new XmlMapper();
        // mapper1.findAndRegisterModules();
        // Model model = mapper1.readValue("", Model.class);
        // System.out.println(labWork1.toString());

        // AddCommand add = new AddCommand();
        // SaveCommand save = new SaveCommand();

        // add.execute(model, add.fillArgs(con));
        System.out.println(model.getById(0));

        // add.execute(model, add.fillArgs(con));
        System.out.println(model.getById(1));

        // save.execute(model, save.fillArgs(con));

        // XmlMapper mapper = new XmlMapper();
        // mapper.findAndRegisterModules();
    	// String jsonBook = mapper.writeValueAsString(labWork);
    	// System.out.println(jsonBook);

    }
}
