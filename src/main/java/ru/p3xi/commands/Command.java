package ru.p3xi.commands;

import ru.p3xi.cm.Model;

public abstract class Command {
    private String name;
    private String description;
    private String args;

    protected Command(String name, String description, String args) {
        this.name = name;
        this.description = description;
        this.args = args;
    }

    public String getArgs() {
        return args;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
    
    public abstract void execute(Model model, Object[] args);
}
