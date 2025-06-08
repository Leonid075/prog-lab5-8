package ru.p3xi.ccommands;

import ru.p3xi.cnet.ClientNet;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.labwork.LabWork;
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
    public CommandRequest fillArgs(VirtualConsole con, String[] args) throws FileEndException {
        try {
            return new CommandRequest.Builder().command(args[0]).build();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void execute(ClientNet net, CommandRequest args) throws ArgsException {
        if (args == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        CommandResponce responce = net.SendRequest(args);
        if (!responce.getIsOk()) {
            System.out.println(responce.getResponce());
        }
        float average = 0;
        for (LabWork labWork : responce.getLabWorks()) {
            average += labWork.getMinimalPoint();
        }
        if (responce.getLabWorks().size() == 0)
            System.out.println("Коллекция пустая");
        else
            System.out.println(
                    "Среднее занчение minimalPoint всех элементов " + ((Float) (average / responce.getLabWorks().size())).toString());
    }
}
