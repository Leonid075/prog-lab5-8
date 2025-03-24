package ru.p3xi.commands;

import java.io.Console;

import ru.p3xi.cm.Model;

public class SaveCommand extends Command {
    public SaveCommand() {
        super("save", "Сохранить коллекию в файл", new Object[] {}, "");
    }

    @Override
    public Object[] fillArgs(Console con) {
        return new Object[] {};
    }

    @Override
    public void execute(Model model, Object[] args) {
        String filename = System.getenv("lab5");
        if (filename == null) {
            System.out.println("Переменная окружения не задана");
        }
        else {
            model.save(filename);
            System.out.println("Коллекция сохранена");
        }
    }
}
