package ru.p3xi.commands;

import ru.p3xi.cm.Model;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;

/**
 * Удаление элемента коллекции по id
 */
public class RemoveByIdCommand extends Command {
    public RemoveByIdCommand() {
        super("remove_by_id", "Удалить элемент коллекции по id", "id");
    }

    @Override
    public CommandRequest fillArgs(VirtualConsole con, String[] args) throws FileEndException {
        long id;
        try {
            id = Long.parseLong(args[1]);
        } catch (NumberFormatException e) {
            return null;
        }
        try {
            return new CommandRequest.Builder().id(id).command(args[0]).build();
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
        model.remove(args.getId());
        System.out.println("Удален объект со значеним id " + args.getId() + " (если он существовал)");
    }
}
