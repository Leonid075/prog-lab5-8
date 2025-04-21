package ru.p3xi.commands;

import java.io.Console;
import java.util.ArrayList;
import java.util.Iterator;

import ru.p3xi.cm.Model;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.labwork.*;

public class PrintDisciplineCommand extends Command {
    public PrintDisciplineCommand() {
        super("print_field_descending_discipline", "Вывести значения discipline всех элементов в порядке убывания",
                new Object[] {}, "");
    }

    @Override
    public Object[] fillArgs(VirtualConsole con) throws FileEndException {
        return new Object[] {};
    }

    @Override
    public void execute(Model model, Object[] args) {
        if (model.getSize() == 0) {
            System.out.println("коллекция пуста");
            return;
        }
        ArrayList<Discipline> disciplines = new ArrayList<>();
        Iterator<LabWork> iterator = model.getLabWorks().iterator();
        while (iterator.hasNext()) {
            LabWork labWork = iterator.next();
            disciplines.add(labWork.getDiscipline());
        }
        System.out.println("Значения discipline всех элементов:");
        for (Discipline discipline : disciplines) {
            System.out.println(discipline.toString());
            ;
        }
    }
}
