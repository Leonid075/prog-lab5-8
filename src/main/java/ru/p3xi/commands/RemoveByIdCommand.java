package ru.p3xi.commands;

import java.io.Console;

import ru.p3xi.cm.Model;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.labwork.LabWork;
import ru.p3xi.labwork.LabWorkBuilder;

public class RemoveByIdCommand extends Command {
    public RemoveByIdCommand() {
        super("remove_by_id", "Удалить элемент коллекции по id", new Object[] { new Long(1l) }, "id");
    }

    @Override
    public Object[] fillArgs(VirtualConsole con) throws FileEndException {
        return new Object[] {};
    }

    @Override
    public void execute(Model model, Object[] args) throws ArgsException {
        long id;
        try {
            id = new Long((String) args[0]).longValue();
        } catch (Exception e) {
            throw new ArgsException("Неверные аргументы команды " + getName());
        }
        model.remove(id);
        System.out.println("Удален объект со значеним id " + id + " (если он существовал)");
    }
}
