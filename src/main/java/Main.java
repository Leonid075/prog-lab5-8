import ru.p3xi.cm.Model;
import ru.p3xi.commands.*;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.console.VirtualRealConsole;
import ru.p3xi.terminal.Terminal;

public class Main {
    public static void main(String[] args) {
        try {
            String var = System.getenv("lab5");
            if (var == null) {
                System.out.println("Переменая окружения 'lab5' не задана");
                return;
            }
            Model model = Model.load(var);
            if (model == null)
                return;

            VirtualConsole con = new VirtualRealConsole();

            Terminal terminal = new Terminal(new Command[] { new AddCommand(), 
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
                    new SaveCommand(), 
                    new ShowCommand(),
                    new UpdateCommand() });

            terminal.execute(model, con);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
