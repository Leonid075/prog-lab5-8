package ru.p3xi.commands;

import java.io.Console;

import ru.p3xi.cm.Model;
import ru.p3xi.console.VirtualConsole;

public class ClearCommand extends Command {
    public ClearCommand() {
        super("clear", "Очистить коллекцию", new Object[] {}, "");
    }

    @Override
    public Object[] fillArgs(VirtualConsole con) {
        return new Object[] {};
    }

    @Override
    public void execute(Model model, Object[] args) {
        model.clear();
        System.out.println("Коллекция очищена");
    }
}
