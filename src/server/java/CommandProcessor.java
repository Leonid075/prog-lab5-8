import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import ru.p3xi.cm.Model;
import ru.p3xi.request.CommandRequest;
import ru.p3xi.request.CommandResponce;
import ru.p3xi.scommands.ArgsException;
import ru.p3xi.scommands.Command;
import ru.p3xi.snet.ServerNet;

public class CommandProcessor {
    private HashMap<String, Command> commands = new HashMap<>();
    private BufferedReader consoleReader;
    private String filename;

    public CommandProcessor(Command[] commands, String sysvar) {
        filename = sysvar;
        consoleReader = new BufferedReader(new InputStreamReader(System.in));
        for (Command i : commands) {
            this.commands.put(i.getName(), i);
        }
    }

    public void execute(Model model, ServerNet net) {
        while (true) {
            try {
                if (consoleReader.ready()) {
                    String consoleInput = consoleReader.readLine();
                    if (consoleInput != null) {
                        if (consoleInput.equals("quit")) {
                            return;
                        }
                        if (consoleInput.equals("save")) {
                            model.save(filename);
                        }
                    }
                }

                if (net.ready()) {
                    CommandRequest request = net.read();
                    if (request == null)
                        continue;
                    System.out.println(request.getCommand());
                    try {
                        Command executed = commands.get(request.getCommand());
                        CommandResponce responce = executed.execute(model, request);
                        net.write(responce);
                    } catch (ArgsException e) {
                        net.write(new CommandResponce.Builder().isOk(false).responce("Неверные аргуметы команды")
                                .build());
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
