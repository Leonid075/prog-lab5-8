package ru.p3xi.commands;

import ru.p3xi.cm.Model;

public class ShowCommand extends Command {
    public ShowCommand() {
        super("show", "Вывести все элементы коллекции", "");
    }

    @Override
    public void execute(Model model, Object[] args) {
        
    }
}
