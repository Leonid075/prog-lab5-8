package ru.p3xi.scommands;

import ru.p3xi.cm.Model;
import ru.p3xi.labwork.LabWork;
import ru.p3xi.request.CommandRequest;
import ru.p3xi.request.CommandResponce;

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
    public CommandResponce execute(Model model, CommandRequest args) throws ArgsException {
        if (args == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        if (args.getLabWork() == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        LabWork.Builder labWork = args.getLabWork();
        labWork.setOwner(args.getUsername());
        boolean res = model.addIfMax(labWork);
        if (res)
            return new CommandResponce.Builder().isOk(true).responce("Объект добавлен в коллекцию").build();
        else
            return new CommandResponce.Builder().isOk(true).responce("Объект не добавлен в коллекцию").build();
    }
}
