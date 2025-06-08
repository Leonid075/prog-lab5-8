package ru.p3xi.console;

/**
 * Исключение- файл прочитан полностью
 */
public class FileEndException extends Exception {
    public FileEndException(String errorMessage) {
        super(errorMessage);
    }
}
