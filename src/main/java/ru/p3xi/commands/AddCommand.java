package ru.p3xi.commands;

import ru.p3xi.cm.Model;
import ru.p3xi.labwork.LabWork;

public class AddCommand extends Command {
    public AddCommand() {
        super("add", "Добавляет новый элемент в коллекцию", "{element}");
    }

    @Override
    public void execute(Model model, Object[] args) {
        LabWork labWork = (LabWork) args[0];
        model.add(labWork);
    }
}
