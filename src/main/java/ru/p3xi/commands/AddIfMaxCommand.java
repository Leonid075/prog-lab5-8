package ru.p3xi.commands;

import ru.p3xi.cm.Model;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.labwork.LabWork;
import ru.p3xi.labwork.LabWorkBuilder;

/**
 * Добавить элемент, если он будет максимальным в коллекции
 */
public class AddIfMaxCommand extends Command {
    public AddIfMaxCommand() {
        super("add_if_max",
                "Добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента",
                "{element}");
    }

    @Override
    public CommandRequest fillArgs(VirtualConsole con, String[] args) throws FileEndException {
        LabWorkBuilder labWorkBuilder = new LabWorkBuilder();
        labWorkBuilder.buildInTerminal(con);
        try {
            return new CommandRequest.Builder().command(args[0]).labWork(new LabWork(labWorkBuilder)).build();
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
        boolean res = model.addIfMax(args.getLabWork());
        if (res)
            System.out.println("Объект добавлен в коллекцию");
        else
            System.out.println("Объект не добавлен в коллекцию");
    }
}
