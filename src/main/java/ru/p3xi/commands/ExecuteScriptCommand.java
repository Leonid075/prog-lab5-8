package ru.p3xi.commands;

import java.util.HashMap;

import ru.p3xi.cm.Model;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.console.VirtualFileConsole;
import ru.p3xi.file.FileContainer;
import ru.p3xi.file.FileException;

public class ExecuteScriptCommand extends Command {
    private VirtualFileConsole fileConsole;
    private HashMap<String, Command> commands;

    public ExecuteScriptCommand(Command[] commands) {
        super("execute_script", "Исполнить скрипт из файла", "filename");

        this.commands = new HashMap<String, Command>();
        for (Command i : commands) {
            this.commands.put(i.getName(), i);
        }
    }

    @Override
    public CommandRequest fillArgs(VirtualConsole con, String[] args) throws FileEndException {
        try {
            fileConsole = new VirtualFileConsole(new FileContainer(args[1]).readFile());
        } catch (FileException e) {
            con.writeLine("Ошибка чтения файла");
        }
        return null;
    }

    @Override
    public void execute(Model model, CommandRequest args) throws ArgsException {
        while (true) {
            String[] console_args = new String[] {};
            try {
                console_args = fileConsole.readLine("").split(" ");
            } catch (FileEndException e) {
                return;
            }

            if (console_args.length == 0 || (console_args.length == 1 && console_args[0].equals("")))
                continue;

            else {
                Command executed = commands.get(console_args[0]);
                if (executed == null) {
                    System.out.println("Команды " + console_args[0] + " нет");
                    System.out.println("Используйте help для получения списка доступных команд");
                }
                try {
                    CommandRequest request = executed.fillArgs(fileConsole, console_args);
                    executed.execute(model, request);
                } catch (FileEndException | ArgsException e) {
                    System.out.println(e);
                    return;
                } catch (Exception e) {
                }
            }
        }
    }
}
