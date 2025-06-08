package ru.p3xi.ccommands;

import ru.p3xi.cnet.ClientNet;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.labwork.LabWork;
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
        for (LabWork labWork : responce.getLabWorks()) {
            System.out.println(labWork.show());
        }
    }
}
