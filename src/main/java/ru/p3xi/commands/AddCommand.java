package ru.p3xi.commands;

import ru.p3xi.cm.Model;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.labwork.LabWork;

/**
 * Создание нового элемента коллекции
 */
public class AddCommand extends Command {
    public AddCommand() {
        super("add", "Добавляет новый элемент в коллекцию", "{element}");
    }

    @Override
    public CommandRequest fillArgs(VirtualConsole con, String[] args) throws FileEndException {
        try {
            return new CommandRequest.Builder().command(args[0]).labWork(LabWork.buildInTerminal(con)).build();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void execute(Model model, CommandRequest args) throws ArgsException {
        if (args == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        if (args.getLabWork() == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        model.add(args.getLabWork());
        System.out.println("Объект добавлен в коллекцию");
    }
}
