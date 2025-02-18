package ru.p3xi.commands;

import ru.p3xi.cm.Model;
import ru.p3xi.labwork.LabWork;

public class UpdateCommand extends Command {
    public UpdateCommand() {
        super("update", "Обновить элемент в коллекции по id", "id {element}");
    }

    @Override
    public void execute(Model model, Object[] args) {
        long id = (long) args[0];
        LabWork labWork = (LabWork) args[1];
        model.update(id, labWork);
    }
}
