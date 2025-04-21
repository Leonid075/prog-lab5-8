package ru.p3xi.commands;

import ru.p3xi.cm.Model;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.labwork.LabWork;

/**
 * Вывести среднее значение minimalPoint всех элементов коллекции
 */
public class AvarageMinPointCommand extends Command {
    public AvarageMinPointCommand() {
        super("average_of_minimal_point", "Вывести среднее значение minimalPoint для всех элементов", new Object[] {},
                "");
    }

    @Override
    public Object[] fillArgs(VirtualConsole con) throws FileEndException {
        return new Object[] {};
    }

    @Override
    public void execute(Model model, Object[] args) {
        float average = 0;
        for (LabWork labWork : model.getLabWorks()) {
            average += labWork.getMinimalPoint();
        }
        if (model.getSize() == 0)
            System.out.println("Коллекция пустая");
        else
            System.out.println(
                    "Среднее занчение minimalPoint всех элементов " + ((Float) (average / model.getSize())).toString());
    }
}
