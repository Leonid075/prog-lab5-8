package ru.p3xi.scommands;

import ru.p3xi.cm.Model;
import ru.p3xi.request.CommandRequest;
import ru.p3xi.request.CommandResponce;

/**
 * Очистить коллекцию
 */
public class ClearCommand extends Command {
    public ClearCommand() {
        super("clear", "Очистить коллекцию", "");
    }

    @Override
    public CommandResponce execute(Model model, CommandRequest args) throws ArgsException {
        if (args == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        model.clear();
        return new CommandResponce.Builder().isOk(true).responce("Коллекция очищена").build();
    }
}
