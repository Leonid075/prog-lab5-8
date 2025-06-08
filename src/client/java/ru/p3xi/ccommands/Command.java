package ru.p3xi.ccommands;

import ru.p3xi.cnet.ClientNet;
import ru.p3xi.console.FileEndException;
import ru.p3xi.console.VirtualConsole;
import ru.p3xi.request.CommandRequest;

/**
 * Абстрактный класс команды со свойствами <b>name</b>, <b>description</b>,
 * <b>args</b>, <b>argsDescription</b>
 */
public abstract class Command {
    /** Название */
    private String name;
    /** Описание */
    private String description;
    /** Описание аргументов */
    private String argsDescription;

    protected Command(String name, String description, String argsDescription) {
        this.name = name;
        this.description = description;
        this.argsDescription = argsDescription;
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
    public abstract CommandRequest fillArgs(VirtualConsole con, String[] args) throws FileEndException;

    /** Вызвать команьду */
    public abstract void execute(ClientNet net, CommandRequest args) throws ArgsException;
}
