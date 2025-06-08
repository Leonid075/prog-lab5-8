package ru.p3xi.scommands;

import ru.p3xi.cm.Model;
import ru.p3xi.request.CommandRequest;
import ru.p3xi.request.CommandResponce;

/**
 * Вывести элементы коллекции
 */
public class ShowCommand extends Command {
    public ShowCommand() {
        super("show", "Вывести все элементы коллекции", "");
    }

    @Override
    public CommandResponce execute(Model model, CommandRequest args) throws ArgsException {
        if (args == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        return new CommandResponce.Builder().isOk(true).labWorks(model.getLabWorks()).build();
    }
}
