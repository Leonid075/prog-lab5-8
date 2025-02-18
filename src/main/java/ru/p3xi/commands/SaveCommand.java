package ru.p3xi.commands;

import ru.p3xi.cm.Model;

public class SaveCommand extends Command {
    public SaveCommand() {
        super("save", "Сохранить коллекию в файл", "");
    }

    @Override
    public void execute(Model model, Object[] args) {
        
    }
}
