import ru.p3xi.ccommands.Command;
import ru.p3xi.ccommands.ExecuteScriptCommand;
import ru.p3xi.ccommands.InfoCommand;
import ru.p3xi.ccommands.PrintDisciplineCommand;
import ru.p3xi.ccommands.RemoveByDiffCommand;
import ru.p3xi.ccommands.RemoveByIdCommand;
import ru.p3xi.ccommands.RemoveLowerCommand;
import ru.p3xi.ccommands.ShowCommand;
import ru.p3xi.ccommands.UpdateCommand;
import ru.p3xi.cnet.ClientNet;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.console.VirtualRealConsole;
import ru.p3xi.terminal.Terminal;
import ru.p3xi.ccommands.AddCommand;
import ru.p3xi.ccommands.AddIfMaxCommand;
import ru.p3xi.ccommands.AddIfMinCommand;
import ru.p3xi.ccommands.AvarageMinPointCommand;
import ru.p3xi.ccommands.ClearCommand;

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

        // AddCommand add = new AddCommand();
        // try {
        // CommandRequest req=add.fillArgs(new VirtualRealConsole(), new String[]
        // {"add"});
        // add.execute(net, req);
        // }
        // catch (Exception e) {
        // System.out.println(e);
        // }
        // System.out.println(responce);
    }
}
