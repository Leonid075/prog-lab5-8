import java.io.IOException;

import ru.p3xi.cm.Model;
import ru.p3xi.scommands.*;
import ru.p3xi.snet.ServerNet;

public class Main {
    public static void main(String[] args) {
        Model model = Model.load();

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
                new UpdateCommand(),
                new CheckUserCommand(),
                new RegisterCommand()
        };
        try {
            ServerNet net = new ServerNet();
            new CommandProcessor(commands).execute(model, net);
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
