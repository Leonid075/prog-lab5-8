package ru.p3xi.commands;

import java.util.ArrayList;
import java.util.Arrays;

import ru.p3xi.cm.Model;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;

/**
 * Абстрактный класс команды со свойствами <b>name</b>, <b>description</b>, <b>args</b>, <b>argsDescription</b>
 */
public abstract class Command {
    /** Название */
    private String name;
    /** Описание */
    private String description;
    /** Принимаемые агументы */
    private ArrayList<Object> args;
    /** Описание аргументов */
    private String argsDescription;

    protected Command(String name, String description, Object[] args, String argsDescription) {
        this.name = name;
        this.description = description;
        this.args = new ArrayList<Object>(Arrays.asList(args));
        this.argsDescription = argsDescription;
    }

    /** Получить аргументы */
    public ArrayList<Object> getArgs() {
        return args;
    }

    /** Получить описание */
    public String getDescription() {
        return description;
    }

    /** Получить имя */
    public String getName() {
        return name;
    }

    /** Получить описанмие аргуметов */
    public String getArgsDescription() {
        return argsDescription;
    }

    /** Заполнить аргументы используя консоль */
    public abstract Object[] fillArgs(VirtualConsole con) throws FileEndException;

    /** Вызвать команьду */
    public abstract void execute(Model model, Object[] args) throws ArgsException;
}
