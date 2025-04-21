package ru.p3xi.commands;

import java.io.Console;

import ru.p3xi.cm.Model;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.labwork.LabWork;
import ru.p3xi.labwork.LabWorkBuilder;

public class AddIfMaxCommand extends Command {
    public AddIfMaxCommand() {
        super("add_if_max",
                "Добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента",
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
        boolean res = model.addIfMax(labWork);
        if (res) System.out.println("Объект добавлен в коллекцию");
        else System.out.println("Объект не добавлен в коллекцию");
    }
}
