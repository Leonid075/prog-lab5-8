package ru.p3xi.ccommands;

import java.util.ArrayList;
import java.util.Iterator;

import ru.p3xi.cnet.ClientNet;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.labwork.*;
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
        if (responce.getLabWorks().size() == 0) {
            System.out.println("коллекция пуста");
            return;
        }
        ArrayList<Discipline> disciplines = new ArrayList<>();
        Iterator<LabWork> iterator = responce.getLabWorks().iterator();
        while (iterator.hasNext()) {
            LabWork labWork = iterator.next();
            disciplines.add(labWork.getDiscipline());
        }
        System.out.println("Значения discipline всех элементов:");
        for (Discipline discipline : disciplines) {
            System.out.println(discipline.toString());
        }
    }
}
