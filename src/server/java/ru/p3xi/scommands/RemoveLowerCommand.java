package ru.p3xi.scommands;

import ru.p3xi.cm.Model;
import ru.p3xi.request.CommandRequest;
import ru.p3xi.request.CommandResponce;

/**
 * Удаляет элементы, меньше заданного
 */
public class RemoveLowerCommand extends Command {
    public RemoveLowerCommand() {
        super("remove_lower", "Удалить из коллекции все элементы, меньшие, чем заданный", "{element}");
    }

    @Override
    public CommandResponce execute(Model model, CommandRequest args) throws ArgsException {
        if (args == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        if (args.getLabWork() == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        model.removeLower(args.getLabWork().setId(0).build());
        return new CommandResponce.Builder().isOk(true)
                .responce("Удалены объекты меньше заданого (если они существовали)").build();
    }
}
