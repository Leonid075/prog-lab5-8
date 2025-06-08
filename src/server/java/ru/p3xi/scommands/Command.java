package ru.p3xi.scommands;

import ru.p3xi.cm.Model;
import ru.p3xi.request.CommandRequest;
import ru.p3xi.request.CommandResponce;

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

    /** Вызвать команьду */
    public abstract CommandResponce execute(Model model, CommandRequest args) throws ArgsException;
}
