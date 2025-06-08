package ru.p3xi.scommands;

import ru.p3xi.cm.Model;
import ru.p3xi.request.CommandRequest;
import ru.p3xi.request.CommandResponce;

/**
 * Обновление элемента коллекции по id
 */
public class UpdateCommand extends Command {
    public UpdateCommand() {
        super("update", "Обновить элемент в коллекции по id", "id, {element}");
    }

    @Override
    public CommandResponce execute(Model model, CommandRequest args) throws ArgsException {
        if (args == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        if (args.getId() == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        if (args.getLabWork() == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        model.update(args.getId(), args.getLabWork());
        return new CommandResponce.Builder().isOk(true)
                .responce("Обновлено значение объекта с id " + args.getId()).build();
    }
}
