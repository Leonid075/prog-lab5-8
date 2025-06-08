package ru.p3xi.scommands;

import ru.p3xi.cm.Model;
import ru.p3xi.request.CommandRequest;
import ru.p3xi.request.CommandResponce;

/**
 * Вывести информацию о коллекции
 */
public class InfoCommand extends Command {
    public InfoCommand() {
        super("info", "Вывести информацию о коллекции", "");
    }

    @Override
    public CommandResponce execute(Model model, CommandRequest args) throws ArgsException {
        if (args == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        return new CommandResponce.Builder().isOk(true).responce("Информация о коллекции:\n"+
                            "| Тип коллекции:" + model.getClass().toString()+
                            "| Время инициализации: " + model.getCreationDate().toString()+
                            "| Количество элементов: " + model.getSize()).build();
    }
}
