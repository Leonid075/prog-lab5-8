package ru.p3xi.commands;

import ru.p3xi.cm.Model;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.labwork.LabWork;
import ru.p3xi.labwork.LabWorkBuilder;

/**
 * Обновление элемента коллекции по id
 */
public class UpdateCommand extends Command {
    public UpdateCommand() {
        super("update", "Обновить элемент в коллекции по id", "id, {element}");
    }

    @Override
    public CommandRequest fillArgs(VirtualConsole con, String[] args) throws FileEndException {
        long id;
        try {
            id = Long.parseLong(args[1]);
        } catch (NumberFormatException e) {
            return null;
        }
        LabWorkBuilder labWorkBuilder = new LabWorkBuilder();
        labWorkBuilder.buildInTerminal(con);
        try {
            return new CommandRequest.Builder().command(args[0]).id(id).labWork(new LabWork(labWorkBuilder)).build();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void execute(Model model, CommandRequest args) throws ArgsException {
        if (args == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        if (args.getId() == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        if (args.getLabWork() == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        model.update(args.getId(), args.getLabWork());
        System.out.println("Обновлено значение объекта с id " + args.getId());
    }
}
