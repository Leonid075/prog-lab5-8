package ru.p3xi.commands;

import java.io.Console;

import ru.p3xi.cm.Model;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.labwork.LabWork;
import ru.p3xi.labwork.LabWorkBuilder;

public class RemoveLowerCommand extends Command {
    public RemoveLowerCommand() {
        super("remove_lower", "Удалить из коллекции все элементы, меньшие, чем заданный",
                new Object[] { new LabWork() }, "{element}");
    }

    @Override
    public Object[] fillArgs(VirtualConsole con) throws FileEndException {
        LabWorkBuilder labWorkBuilder = new LabWorkBuilder();
        labWorkBuilder.buildInTerminal(con);
        try {
            return new Object[] { new LabWork(labWorkBuilder) };
        } catch (Exception e) {
            return new Object[] {};
        }
    }

    @Override
    public void execute(Model model, Object[] args) throws ArgsException {
        LabWork labWork;
        try {
            labWork = (LabWork) args[0];
        } catch (Exception e) {
            throw new ArgsException("Неверные аргументы команды " + getName());
        }
        model.removeLower(labWork);
        System.out.println("Удалены объекты меньше заданого (если они существовали)");
    }
}
