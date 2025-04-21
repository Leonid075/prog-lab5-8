package ru.p3xi.commands;

import java.io.Console;

import ru.p3xi.cm.Model;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.labwork.LabWork;

public class ShowCommand extends Command {
    public ShowCommand() {
        super("show", "Вывести все элементы коллекции", new Object[] {}, "");
    }

    @Override
    public Object[] fillArgs(VirtualConsole con) throws FileEndException {
        return new Object[] {};
    }

    @Override
    public void execute(Model model, Object[] args) {
        if (model.getSize()==0) {
            System.out.println("Коллекция пуста");
            return;
        }
        System.out.println("Элементы коллекции:");
        for (LabWork labWork : model.getLabWorks()) {
            System.out.println(labWork.show());
        }
    }
}
