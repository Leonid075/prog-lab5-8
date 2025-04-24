package ru.p3xi.commands;

import ru.p3xi.cm.Model;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.labwork.LabWork;

/**
 * Вывести элементы коллекции
 */
public class ShowCommand extends Command {
    public ShowCommand() {
        super("show", "Вывести все элементы коллекции", "");
    }

    @Override
    public CommandRequest fillArgs(VirtualConsole con, String[] args) throws FileEndException {
        try {
            return new CommandRequest.Builder().command(args[0]).build();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void execute(Model model, CommandRequest args) throws ArgsException {
        if (args == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        if (model.getSize() == 0) {
            System.out.println("Коллекция пуста");
            return;
        }
        System.out.println("Элементы коллекции:");
        for (LabWork labWork : model.getLabWorks()) {
            System.out.println(labWork.show());
        }
    }
}
