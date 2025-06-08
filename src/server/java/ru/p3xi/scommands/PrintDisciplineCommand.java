package ru.p3xi.scommands;

import ru.p3xi.cm.Model;
import ru.p3xi.request.CommandRequest;
import ru.p3xi.request.CommandResponce;

/**
 * Вывести значения discipline всех элементов коллекции
 */
public class PrintDisciplineCommand extends Command {
    public PrintDisciplineCommand() {
        super("print_field_descending_discipline", "Вывести значения discipline всех элементов в порядке убывания", "");
    }

    @Override
    public CommandResponce execute(Model model, CommandRequest args) throws ArgsException {
        if (args == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        return new CommandResponce.Builder().isOk(true).labWorks(model.getLabWorks()).build();
    }
}
