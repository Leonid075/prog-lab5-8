package ru.p3xi.commands;

import java.io.Console;

import ru.p3xi.cm.Model;
import ru.p3xi.labwork.LabWork;
import ru.p3xi.labwork.LabWorkBuilder;

public class AddIfMinCommand extends Command {
    public AddIfMinCommand() {
        super("add_if_min", "Добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента",
                new Object[] { new LabWork() }, "{element}");
    }

    @Override
    public Object[] fillArgs(Console con) {
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
        model.addIfMin(labWork);
        System.out.println("Объект добавлен в коллекцию");
    }
}
