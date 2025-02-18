package ru.p3xi.commands;

import ru.p3xi.cm.Model;

public class ClearCommand extends Command {
    public ClearCommand() {
        super("clear", "Очистить коллекцию", "");
    }

    @Override
    public void execute(Model model, Object[] args) {
        model.clear();
    }
}
