import ru.p3xi.cm.Model;
import ru.p3xi.commands.AddCommand;
import ru.p3xi.commands.AddIfMaxCommand;
import ru.p3xi.commands.AddIfMinCommand;
import ru.p3xi.commands.ArgsException;
import ru.p3xi.commands.AvarageMinPointCommand;
import ru.p3xi.commands.ClearCommand;
import ru.p3xi.commands.Command;
import ru.p3xi.commands.InfoCommand;
import ru.p3xi.commands.PrintDisciplineCommand;
import ru.p3xi.commands.RemoveByDiffCommand;
import ru.p3xi.commands.RemoveByIdCommand;
import ru.p3xi.commands.RemoveLowerCommand;
import ru.p3xi.commands.SaveCommand;
import ru.p3xi.commands.ShowCommand;
import ru.p3xi.commands.UpdateCommand;
import ru.p3xi.labwork.*;
import ru.p3xi.terminal.Terminal;

import java.io.Console;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
        // Console con = System.console();

        Model model = Model.load(System.getenv("lab5"));
        if (model == null) return;
        // System.out.println(model.getLabWorks());

        Terminal terminal = new Terminal(new Command[] {new AddCommand(), new AddIfMaxCommand(), new AddIfMinCommand(), new AvarageMinPointCommand(), new ClearCommand(), new InfoCommand(), new PrintDisciplineCommand(), new RemoveByDiffCommand(), new RemoveByIdCommand(), new RemoveByIdCommand(), new RemoveLowerCommand(), new SaveCommand(), new ShowCommand(), new UpdateCommand()});

        terminal.execute(model);
        // System.out.println(con.readLine().split(" ")[0]);

        // XmlMapper mapper1 = new XmlMapper();
        // mapper1.findAndRegisterModules();
        // Model model = mapper1.readValue("", Model.class);
        // System.out.println(labWork1.toString());

        // AddCommand add = new AddCommand();
        // SaveCommand save = new SaveCommand();
        // UpdateCommand update = new UpdateCommand();

        // update.execute(model, update.fillArgs(con));

        // add.execute(model, add.fillArgs(con));
        // System.out.println(model.getById(0).show());

        // add.execute(model, add.fillArgs(con));
        // System.out.println(model.getById(1).show());

        // save.execute(model, save.fillArgs(con));

        // XmlMapper mapper = new XmlMapper();
        // mapper.findAndRegisterModules();
    	// String jsonBook = mapper.writeValueAsString(labWork);
    	// System.out.println(jsonBook);

    }
}
