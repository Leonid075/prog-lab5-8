package ru.p3xi.scommands;

import ru.p3xi.cm.Model;
import ru.p3xi.request.CommandRequest;
import ru.p3xi.request.CommandResponce;

/**
 * Удаление элемента коллекции по id
 */
public class RemoveByIdCommand extends Command {
    public RemoveByIdCommand() {
        super("remove_by_id", "Удалить элемент коллекции по id", "id");
    }

    @Override
    public CommandResponce execute(Model model, CommandRequest args) throws ArgsException {
        if (args == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        if (args.getId() == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        model.remove(args.getId());
        return new CommandResponce.Builder().isOk(true)
                .responce("Удален объект со значеним id " + args.getId() + " (если он существовал)").build();
    }
}
