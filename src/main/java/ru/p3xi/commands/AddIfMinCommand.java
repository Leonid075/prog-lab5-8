package ru.p3xi.commands;

import ru.p3xi.cm.Model;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.labwork.LabWork;

/**
 * Добавить элемент, если он будет минимальным в коллекции
 */
public class AddIfMinCommand extends Command {
    public AddIfMinCommand() {
        super("add_if_min", "Добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента",
                "{element}");
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
        boolean res = model.addIfMin(args.getLabWork().setId(0).build());
        if (res)
            System.out.println("Объект добавлен в коллекцию");
        else
            System.out.println("Объект не добавлен в коллекцию");
    }
}
