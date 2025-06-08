package ru.p3xi.ccommands;

/**
 * Исключение- неправильные аргументы, переданные команде
 */
public class ArgsException extends Exception {
    public ArgsException(String errorMessage) {
        super(errorMessage);
    }
}
