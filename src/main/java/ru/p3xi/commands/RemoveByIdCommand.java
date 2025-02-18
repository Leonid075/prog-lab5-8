package ru.p3xi.commands;

import ru.p3xi.cm.Model;

public class RemoveByIdCommand extends Command {
    public RemoveByIdCommand() {
        super("remove_by_id", "Удалить элемент коллекции по id", "id");
    }

    @Override
    public void execute(Model model, Object[] args) {
        long id = (long) args[0];
        model.remove(id);
    }
}
