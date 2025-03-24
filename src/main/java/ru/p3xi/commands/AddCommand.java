package ru.p3xi.commands;

import java.io.Console;

import ru.p3xi.cm.Model;
import ru.p3xi.labwork.LabWork;
import ru.p3xi.labwork.LabWorkBuilder;

public class AddCommand extends Command {
    public AddCommand() {
        super("add", "Добавляет новый элемент в коллекцию", new Object[] { new LabWork() }, "{element}");
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
        model.add(labWork);
        System.out.println("Объект добавлен в коллекцию");
    }
}
