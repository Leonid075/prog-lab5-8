package ru.p3xi.commands;

import java.io.Console;

import ru.p3xi.cm.Model;

public class InfoCommand extends Command {
    public InfoCommand() {
        super("info", "Вывести информацию о коллекции", new Object[] {}, "");
    }

    @Override
    public Object[] fillArgs(Console con) {
        return new Object[] {};
    }

    @Override
    public void execute(Model model, Object[] args) {
        System.out.println("Информация о коллекции:");
        System.out.println("| Тип коллекции: " + model.getClass().toString());
        System.out.println("| Время инициализации: " + model.getCreationDate().toString());
        System.out.println("| Количество элементов: " + model.getSize());
    }
}
