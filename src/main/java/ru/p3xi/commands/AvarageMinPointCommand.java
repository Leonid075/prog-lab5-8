package ru.p3xi.commands;

import ru.p3xi.cm.Model;
import ru.p3xi.labwork.LabWork;

public class AvarageMinPointCommand extends Command {
    public AvarageMinPointCommand() {
        super("average_of_minimal_point", "Вывести среднее значение minimalPoint для всех элементов", "");
    }

    @Override
    public void execute(Model model, Object[] args) {
        float average = 0;
        for (LabWork labWork : model.getAll()) {
            average += labWork.getMinimalPoint();
        }
        ((Float)(average / model.getAll().size())).toString();
    }
}
