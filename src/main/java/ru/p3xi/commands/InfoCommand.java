package ru.p3xi.commands;

import ru.p3xi.cm.Model;

public class InfoCommand extends Command {
    public InfoCommand() {
        super("info", "Вывести информацию о коллекции", "");
    }

    @Override
    public void execute(Model model, Object[] args) {
        
    }
}
