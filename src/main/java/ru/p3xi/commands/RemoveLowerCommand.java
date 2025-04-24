package ru.p3xi.commands;

import ru.p3xi.cm.Model;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.labwork.LabWork;

/**
 * Удаляет элементы, меньше заданного
 */
public class RemoveLowerCommand extends Command {
    public RemoveLowerCommand() {
        super("remove_lower", "Удалить из коллекции все элементы, меньшие, чем заданный", "{element}");
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
        model.removeLower(args.getLabWork().setId(0).build());
        System.out.println("Удалены объекты меньше заданого (если они существовали)");
    }
}
