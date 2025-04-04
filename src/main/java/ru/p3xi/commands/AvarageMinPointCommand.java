package ru.p3xi.commands;

import java.io.Console;

import ru.p3xi.cm.Model;
import ru.p3xi.labwork.LabWork;

public class AvarageMinPointCommand extends Command {
    public AvarageMinPointCommand() {
        super("average_of_minimal_point", "Вывести среднее значение minimalPoint для всех элементов", new Object[] {},
                "");
    }

    @Override
    public Object[] fillArgs(Console con) {
        return new Object[] {};
    }

    @Override
    public void execute(Model model, Object[] args) {
        float average = 0;
        for (LabWork labWork : model.getLabWorks()) {
            average += labWork.getMinimalPoint();
        }
        System.out.println("Среднее занчение minimalPoint всех элементов" + ((Float) (average / model.getLabWorks().size())).toString());
    }
}
