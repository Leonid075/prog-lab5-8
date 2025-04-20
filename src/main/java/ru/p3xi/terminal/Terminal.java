package ru.p3xi.terminal;

import java.io.Console;
import java.util.HashMap;

import ru.p3xi.cm.Model;
import ru.p3xi.commands.*;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.console.*;

public class Terminal {
    HashMap<String, Command> commands = new HashMap<>();
    public Terminal(Command... commands) {
        for (Command i : commands) {
            this.commands.put(i.getName(), i);
        }
    }

    public void execute(Model model) {
        VirtualConsole con = new VirtualRealConsole();
        while (true) {
            String[] args = con.readLine("> ").split(" ");
            if (args.length == 0) continue;
            if(args[0].equals("help")) {
                System.out.println("Доступные команды:");
                for (Command i : commands.values()) {
                    System.out.println(" "+i.getName()+" "+i.getArgsDescription()+" - "+i.getDescription());
                }
            }
            else if(args[0].equals("quit") || args[0].equals("psg")) {
                return;
            }
            else {
                try {
                    Command executed = commands.get(args[0]);
                    if (executed == null) {
                        System.out.println("Команды "+args[0]+" нет");
                        System.out.println("Используйте help для получения списка доступных команд");
                    }
                    executed.execute(model, executed.fillArgs(con));
                }
                catch (Exception e) {

                }
            }
        }
    }
}
