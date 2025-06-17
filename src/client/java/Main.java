import ru.p3xi.cnet.ClientNet;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.console.VirtualRealConsole;
import ru.p3xi.terminal.Terminal;
import ru.p3xi.ccommands.*;

public class Main {
    public static void main(String[] args) {
        try {
            ClientNet net = new ClientNet();

            VirtualConsole con = new VirtualRealConsole();

            Terminal terminal = new Terminal(new Command[] {
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
                    new ExecuteScriptCommand(new Command[] {
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
                    })
            });

            terminal.execute(net, con);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
