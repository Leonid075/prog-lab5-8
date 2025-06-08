import java.io.IOException;

import ru.p3xi.cm.Model;
import ru.p3xi.scommands.*;
import ru.p3xi.snet.ServerNet;

public class Main {
    public static void main(String[] args) {

        String var = System.getenv("lab5");
        if (var == null) {
            System.out.println("Переменая окружения 'lab5' не задана");
            return;
        }
        Model model = Model.load(var);
        if (model == null)
            return;

        Command[] commands = new Command[] {
                new AddCommand(),
                new AddIfMaxCommand(),
                new AddIfMinCommand(),
                new AvarageMinPointCommand(),
                new ClearCommand(),
                new InfoCommand(),
                new PrintDisciplineCommand(),
                new RemoveByDiffCommand(),
                new RemoveByIdCommand(),
                new RemoveByIdCommand(),
                new RemoveLowerCommand(),
                new ShowCommand(),
                new UpdateCommand()
        };
        try {
            ServerNet net = new ServerNet();
            new CommandProcessor(commands, var).execute(model, net);
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
