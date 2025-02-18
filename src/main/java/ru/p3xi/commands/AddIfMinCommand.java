package ru.p3xi.commands;

import ru.p3xi.cm.Model;
import ru.p3xi.labwork.LabWork;

public class AddIfMinCommand extends Command {
    public AddIfMinCommand() {
        super("add_if_min", "Добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента", "{element}");
    }

    @Override
    public void execute(Model model, Object[] args) {
        LabWork labWork = (LabWork) args[0];
        model.addIfMin(labWork);
    }
}
