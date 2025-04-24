package ru.p3xi.terminal;

import java.util.HashMap;

import ru.p3xi.cm.Model;
import ru.p3xi.commands.*;
import ru.p3xi.console.*;

/**
 * Класс терминала, для обработки пользовательского ввода
 */
public class Terminal {
    /** Словарь команд */
    HashMap<String, Command> commands = new HashMap<>();

    public Terminal(Command[] commands) {
        for (Command i : commands) {
            this.commands.put(i.getName(), i);
        }
    }

    /**
     * Запуск терминала
     * 
     * @param model
     * @param con
     */
    public void execute(Model model, VirtualConsole con) {
        while (true) {
            String[] args = new String[] {};
            try {
                args = con.readLine("> ").split(" ");
            } catch (FileEndException e) {
                return;
            }

            if (args.length == 0 || (args.length == 1 && args[0].equals("")))
                continue;

            if (args[0].equals("help")) {
                System.out.println("Доступные команды:");
                for (Command i : commands.values()) {
                    System.out.println(" " + i.getName() + " " + i.getArgsDescription() + " - " + i.getDescription());
                }
            }

            else if (args[0].equals("quit") || args[0].equals("exit") || args[0].equals("psg"))
                return;

            else {
                Command executed = commands.get(args[0]);
                if (executed == null) {
                    System.out.println("Команды " + args[0] + " нет");
                    System.out.println("Используйте help для получения списка доступных команд");
                }
                try {
                    CommandRequest request = executed.fillArgs(con, args);
                    executed.execute(model, request);
                } catch (FileEndException | ArgsException e) {
                    System.out.println(e);
                } catch (Exception e) {
                }
            }
        }
    }
}
