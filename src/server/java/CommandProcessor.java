import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import ru.p3xi.cm.Model;
import ru.p3xi.scommands.Command;
import ru.p3xi.snet.ServerNet;

public class CommandProcessor {
    private HashMap<String, Command> commands = new HashMap<>();
    private BufferedReader consoleReader;

    public CommandProcessor(Command[] commands) {
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
                    }
                }

                net.process(commands, model);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
