package ru.p3xi.scommands;

import ru.p3xi.cm.Model;
import ru.p3xi.request.CommandRequest;
import ru.p3xi.request.CommandResponce;

/**
 * Добавить элемент, если он будет минимальным в коллекции
 */
public class AddIfMinCommand extends Command {
    public AddIfMinCommand() {
        super("add_if_min", "Добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента",
                "{element}");
    }

    @Override
    public CommandResponce execute(Model model, CommandRequest args) throws ArgsException {
        if (args == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        if (args.getLabWork() == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        boolean res = model.addIfMin(args.getLabWork().setId(0).build());
        if (res)
            return new CommandResponce.Builder().isOk(true).responce("Объект добавлен в коллекцию").build();
        else
            return new CommandResponce.Builder().isOk(true).responce("Объект не добавлен в коллекцию").build();
    }
}
