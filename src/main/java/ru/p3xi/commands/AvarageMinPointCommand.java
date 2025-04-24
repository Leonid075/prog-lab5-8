package ru.p3xi.commands;

import ru.p3xi.cm.Model;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.labwork.LabWork;

/**
 * Вывести среднее значение minimalPoint всех элементов коллекции
 */
public class AvarageMinPointCommand extends Command {
    public AvarageMinPointCommand() {
        super("average_of_minimal_point", "Вывести среднее значение minimalPoint для всех элементов", "");
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
        float average = 0;
        for (LabWork labWork : model.getLabWorks()) {
            average += labWork.getMinimalPoint();
        }
        if (model.getSize() == 0)
            System.out.println("Коллекция пустая");
        else
            System.out.println(
                    "Среднее занчение minimalPoint всех элементов " + ((Float) (average / model.getSize())).toString());
    }
}
