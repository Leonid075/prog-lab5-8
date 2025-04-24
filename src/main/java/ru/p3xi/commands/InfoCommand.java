package ru.p3xi.commands;

import ru.p3xi.cm.Model;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;

/**
 * Вывести информацию о коллекции
 */
public class InfoCommand extends Command {
    public InfoCommand() {
        super("info", "Вывести информацию о коллекции", "");
    }

    @Override
    public CommandRequest fillArgs(VirtualConsole con, String[] args) throws FileEndException {
        try {
            return new CommandRequest.Builder().command(args[0]).build();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void execute(Model model, CommandRequest args) throws ArgsException {
        if (args == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        System.out.println("Информация о коллекции:");
        System.out.println("| Тип коллекции: " + model.getClass().toString());
        System.out.println("| Время инициализации: " + model.getCreationDate().toString());
        System.out.println("| Количество элементов: " + model.getSize());
    }
}
