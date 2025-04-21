package ru.p3xi.commands;

import java.util.ArrayList;
import java.util.Arrays;

import ru.p3xi.cm.Model;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;

public abstract class Command {
    private String name;
    private String description;
    private ArrayList<Object> args;
    private String argsDescription;

    protected Command(String name, String description, Object[] args, String argsDescription) {
        this.name = name;
        this.description = description;
        this.args = new ArrayList<Object>(Arrays.asList(args));
        this.argsDescription = argsDescription;
    }

    public ArrayList<Object> getArgs() {
        return args;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getArgsDescription() {
        return argsDescription;
    }

    public abstract Object[] fillArgs(VirtualConsole con) throws FileEndException;

    public abstract void execute(Model model, Object[] args) throws ArgsException;
}
