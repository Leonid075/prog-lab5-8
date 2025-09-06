package ru.p3xi.scommands;

import ru.p3xi.cm.Model;
import ru.p3xi.request.CommandRequest;
import ru.p3xi.request.CommandResponce;
import ru.p3xi.labwork.LabWork;

/**
 * Создание нового элемента коллекции
 */
public class AddCommand extends Command {
    public AddCommand() {
        super("add", "Добавляет новый элемент в коллекцию", "{element}");
    }

    @Override
    public CommandResponce execute(Model model, CommandRequest args) throws ArgsException {
        if (args == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        if (args.getLabWork() == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        LabWork.Builder labWork = args.getLabWork();
        labWork.setOwner(args.getUsername());
        model.add(labWork);
        return new CommandResponce.Builder().isOk(true).responce("Объект добавлен в коллекцию").build();
    }
}
