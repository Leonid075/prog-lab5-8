package ru.p3xi.commands;

import ru.p3xi.cm.Model;
import ru.p3xi.labwork.LabWork;

public class AddIfMaxCommand extends Command {
    public AddIfMaxCommand() {
        super("add_if_max", "Добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента", "{element}");
    }

    @Override
    public void execute(Model model, Object[] args) {
        LabWork labWork = (LabWork) args[0];
        model.addIfMax(labWork);
    }
}
