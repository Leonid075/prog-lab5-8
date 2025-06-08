package ru.p3xi.ccommands;

import ru.p3xi.cnet.ClientNet;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.labwork.LabWork;
import ru.p3xi.request.CommandRequest;
import ru.p3xi.request.CommandResponce;

/**
 * Создание нового элемента коллекции
 */
public class AddCommand extends Command {
    public AddCommand() {
        super("add", "Добавляет новый элемент в коллекцию", "{element}");
    }

    @Override
    public CommandRequest fillArgs(VirtualConsole con, String[] args) throws FileEndException {
        try {
            return new CommandRequest.Builder().command(args[0]).labWork(LabWork.buildInTerminal(con)).build();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void execute(ClientNet net, CommandRequest args) throws ArgsException {
        if (args == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        if (args.getLabWork() == null)
            throw new ArgsException("Неверные аргументы команды " + getName());
        CommandResponce responce = net.SendRequest(args);
        System.out.println(responce.getResponce());
    }
}
