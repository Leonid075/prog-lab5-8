package ru.p3xi.commands;

import java.io.Console;

import ru.p3xi.cm.Model;
import ru.p3xi.labwork.LabWork;

public class ShowCommand extends Command {
    public ShowCommand() {
        super("show", "Вывести все элементы коллекции", new Object[] {}, "");
    }

    @Override
    public Object[] fillArgs(Console con) {
        return new Object[] {};
    }

    @Override
    public void execute(Model model, Object[] args) {
        
    }
}
