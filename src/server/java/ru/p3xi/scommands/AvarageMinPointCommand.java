package ru.p3xi.scommands;

import ru.p3xi.cm.Model;
import ru.p3xi.request.CommandRequest;
import ru.p3xi.request.CommandResponce;

/**
 * Вывести среднее значение minimalPoint всех элементов коллекции
 */
public class AvarageMinPointCommand extends Command {
    public AvarageMinPointCommand() {
        super("average_of_minimal_point", "Вывести среднее значение minimalPoint для всех элементов", "");
    }

    @Override
    public CommandResponce execute(Model model, CommandRequest args) throws ArgsException {
        if (args == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        return new CommandResponce.Builder().isOk(true).labWorks(model.getLabWorks()).build();
    }
}
