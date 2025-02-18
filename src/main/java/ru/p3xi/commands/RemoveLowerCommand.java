package ru.p3xi.commands;

import ru.p3xi.cm.Model;
import ru.p3xi.labwork.LabWork;

public class RemoveLowerCommand extends Command {
    public RemoveLowerCommand() {
        super("remove_lower", "Удалить из коллекции все элементы, меньшие, чем заданный", "{element}");
    }

    @Override
    public void execute(Model model, Object[] args) {
        LabWork labWork = (LabWork) args[0];
        model.removeLower(labWork);
    }
}
